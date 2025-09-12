package productoPrincipal.Configs;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import principalcommons.ProductoEvent;

@Component
@RequiredArgsConstructor
public class KafkaProductoEventoProducer {
    private final KafkaTemplate<String, ProductoEvent> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(KafkaProductoEventoProducer.class);
    public void enviarProductoEvento(ProductoEvent productoEvent){
        kafkaTemplate.send("producto-eventos",productoEvent);
        System.out.println("Evento de PRODUCTO-SERVICE enviado a kafka: "+productoEvent);
        logger.info("Evento de PRODUCTO-SERVICE enviado a kafka: ", productoEvent);
    }
}
