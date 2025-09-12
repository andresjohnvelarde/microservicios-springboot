package productoPrincipal.Configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import principalcommons.OrdenEvent;

@Component
public class OrdenEventoListener {
    private static final Logger logger = LoggerFactory.getLogger(OrdenEventoListener.class);
    @RabbitListener(queues = RabbiitMQConfig.QUEUE)
    public void handlerOrdenEvento(OrdenEvent event){
        System.out.printf("Orden recibida desde RabbitMQ: %s%n", event);
        // Acá se haría todo el proceso que uno necesite hacer con el mensaje recibido
        logger.info("Orden recibida desde RabbitMQ: ",event);
    }
}
