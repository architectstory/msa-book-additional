package com.hoony.msa.order.system.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQSender {

    private Logger logger = LoggerFactory.getLogger(RabbitMQSender.class);

    private RabbitMessagingTemplate rabbitMessagingTemplate;

    //constructor
    public RabbitMQSender(RabbitMessagingTemplate rabbitMessagingTemplate) {
        this.rabbitMessagingTemplate = rabbitMessagingTemplate;
    }

    public void send(RabbitMQMessage rabbitMQMessage) {
        rabbitMessagingTemplate.convertAndSend(RabbitMQConfiguration.SEND_MESSAGE, rabbitMQMessage);
        logger.debug("RabbitMQSender RabbitMQ Message is sended : ===>  " + rabbitMQMessage);
    }
}
