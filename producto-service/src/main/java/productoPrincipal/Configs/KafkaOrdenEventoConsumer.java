package productoPrincipal.Configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import principalcommons.OrdenEvent;

@Component
public class KafkaOrdenEventoConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaOrdenEventoConsumer.class);
    @KafkaListener(topics = "orden-eventos", groupId = "producto-service-orden-group")
    public void consumir(OrdenEvent evento){
        System.out.println("Evento recibido de kafka: "+evento);
        logger.info("Evento recibido de kafka: ",evento);
    }
}
