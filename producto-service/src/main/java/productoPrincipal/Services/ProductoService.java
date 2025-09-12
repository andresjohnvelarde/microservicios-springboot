package productoPrincipal.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import principalcommons.ProductoEvent;
import productoPrincipal.Configs.KafkaOrdenEventoConsumer;
import productoPrincipal.Configs.KafkaProductoEventoProducer;
import productoPrincipal.Models.Producto;
import productoPrincipal.Repositories.ProductoRepository;

import java.util.List;

@Service
public class ProductoService {
    private static final Logger logger = LoggerFactory.getLogger(ProductoService.class);
    @Autowired
    private ProductoRepository repository;

    private KafkaProductoEventoProducer kafkaProductoEventoProducer;

    public Producto registrar(Producto prod) {
        logger.info("Se procede a registrar un producto: ", prod);
        
        kafkaProductoEventoProducer.enviarProductoEvento(
                new ProductoEvent(
                        prod.getId(),
                        prod.getNombre(),
                        prod.getPrecio()
                )
        );
        return repository.save(prod);
    }

    public Producto getById(Long id) {
        logger.info("Se envían datos del producto con ID: ", id);
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public List<Producto> getAll() {
        logger.info("Se solicitó la lista de todos los productos");
        return repository.findAll();
    }
}

