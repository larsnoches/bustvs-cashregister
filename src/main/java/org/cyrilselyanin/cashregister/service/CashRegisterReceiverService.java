package org.cyrilselyanin.cashregister.service;

import org.cyrilselyanin.cashregister.dto.TicketDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Cash register receiver, annotated as rabbit listener.
 */
@Service
@RabbitListener(queues = "#{autoDeletingQueue.name}")
public class CashRegisterReceiverService {
    Logger logger = LoggerFactory.getLogger(CashRegisterReceiverService.class);

    /**
     * Receive method
     * @param in Some json object
     */
    @RabbitHandler
    public void receive(TicketDto in) {
        logger.debug("Incoming ticket dto");
        logger.debug("{}", in);
    }
}
