package principalOrden.Configs;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import principalcommons.OrdenEvent;

@Component
@RequiredArgsConstructor // o si no tenemos Lombok, crear un constructor
public class KafkaOrdenEventoProducer {
    private final KafkaTemplate<String, OrdenEvent> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(KafkaOrdenEventoProducer.class);
    public void enviarOrdenEvento(OrdenEvent ordenEvent){
        kafkaTemplate.send("orden-eventos",ordenEvent);
        System.out.println("Evento enviado a kafka: "+ordenEvent);
        logger.info("Evento enviado a kafka: ", ordenEvent);
    }
}
