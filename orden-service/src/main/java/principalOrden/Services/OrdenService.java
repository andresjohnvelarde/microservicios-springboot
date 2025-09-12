package principalOrden.Services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import principalOrden.Configs.KafkaOrdenEventoProducer;
import principalOrden.Configs.OrdenEventoPublisher;
import principalOrden.Configs.ProductoClient;
import principalOrden.DTOs.Producto;
import principalOrden.Models.Orden;
import principalOrden.Repositories.OrdenRepository;
import principalcommons.OrdenEvent;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrdenRepository ordenRepository;

    private final ProductoClient productoClient;

    private final OrdenEventoPublisher ordenEventoPublisher;

    private final KafkaOrdenEventoProducer kafkaProducer;

    public List<Orden> getTodasLasOrdenes(){
        return ordenRepository.findAll();
    }

    @CircuitBreaker(name="productoServiceCB", fallbackMethod = "fallbackObtenerProducto")
    public Orden registrarOrden(Orden orden){
        // llamar al microservicio de productos
        //  String productoUrl = "http://localhost:8091/producto/"+orden.getProductoId();
        // Producto producto = restTemplate.getForObject(productoUrl, Producto.class);

        // Llamar al microservicio por Eureka
        Producto producto = productoClient.obtenerProducto(orden.getProductoId());

        if(producto==null) throw new RuntimeException("Producto no encontrado");
        orden.setTotal(producto.getPrecio()*orden.getCantidad());
        Orden ordenGuardar = ordenRepository.save(orden);
        ordenEventoPublisher.publicarOrdenEvento(
                new OrdenEvent(ordenGuardar.getId(),
                        ordenGuardar.getProductoId(),
                        ordenGuardar.getCantidad())
        );

        //Con apache Kafka

        kafkaProducer.enviarOrdenEvento(
                new OrdenEvent(
                        ordenGuardar.getId(),
                        ordenGuardar.getProductoId(),
                        ordenGuardar.getCantidad()
                )
        );

        return ordenGuardar;
    }

    public Orden fallbackObtenerProducto(Orden orden, Throwable throwable){
        System.out.println("Fallback originado por: "+throwable.getMessage());
        Orden fallbackOrden = new Orden();
        fallbackOrden.setProductoId(orden.getProductoId());
        fallbackOrden.setCantidad(orden.getCantidad());
        fallbackOrden.setTotal(-1.0);
        orden.setTotal(-1.0); // Corrección? :)
        return ordenRepository.save(orden);
    }
}

// Imágenes de Docker a descargar:
// openzipkin/zipkin
// grafana/grafana
// prom/prometheus