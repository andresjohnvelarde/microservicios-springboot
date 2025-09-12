package principalOrden.Configs;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import principalcommons.OrdenEvent;

@Component
@RequiredArgsConstructor
public class OrdenEventoPublisher {
    private final RabbitTemplate rabbitTemplate;
    private static final Logger logger = LoggerFactory.getLogger(KafkaOrdenEventoProducer.class);
    public void publicarOrdenEvento(OrdenEvent evento){
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                evento
        );
        System.out.println("Evento publicado: "+evento);
        logger.info("Evento publicado en RabbitMQ: ", evento);
    }
}
