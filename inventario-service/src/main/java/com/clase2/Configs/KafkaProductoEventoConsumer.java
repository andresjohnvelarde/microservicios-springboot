package com.clase2.Configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import principalcommons.OrdenEvent;
import principalcommons.ProductoEvent;

public class KafkaProductoEventoConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProductoEventoConsumer.class);
    @KafkaListener(topics = "producto-eventos", groupId = "inventario-group")
    public void consumir(ProductoEvent evento){
        System.out.println("Evento recibido de kafka producido por PRODUCTO-SERVICE: "+evento);
        logger.info("Evento recibido de kafka producido por PRODUCTO-SERVICE: ",evento);
    }
}
