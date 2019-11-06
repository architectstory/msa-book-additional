package com.hoony.msa.status.system.queue;

import com.hoony.msa.status.domain.vo.StatusVO;
import com.hoony.msa.status.springboot.service.StatusServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RabbitMQReceiver {

    private Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class);

    @Autowired
    private StatusServiceImpl statusServiceImpl;

    @RabbitListener(queues = RabbitMQConfiguration.RECEIVER_MESSAGE)
    public void receiver(RabbitMQMessage rabbitMQMessage) {

        if(null == rabbitMQMessage.get_id() || "".equals(rabbitMQMessage.get_id())) {
            logger.debug("Queue Message from Sender : rabbitMQMessage.get_id is -> " + rabbitMQMessage.get_id());
        } else {
            //RabbitMQ Message to VO, Order -> Status
            StatusVO statusVO = new StatusVO();
            statusVO.set_id(UUID.randomUUID().toString()); //status ID
            statusVO.setStatusName(rabbitMQMessage.getStatusName());
            statusVO.setOrderId(rabbitMQMessage.getOrderId());
            statusVO.setOrderName(rabbitMQMessage.getOrderName());
            statusVO.setUserId(rabbitMQMessage.getUserId());
            statusVO.setUserName(rabbitMQMessage.getUserName());
            StatusVO resultStatusVO = statusServiceImpl.addStatus(statusVO);
            logger.debug("Queue Message from Sender : resultStatusVO is ==> " + resultStatusVO);
        }
    }
}
