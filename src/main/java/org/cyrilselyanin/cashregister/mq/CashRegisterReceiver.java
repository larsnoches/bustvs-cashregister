package org.cyrilselyanin.cashregister.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * Cash register receiver, annotated as rabbit listener.
 */
@RabbitListener(queues = "#{autoDeletingQueue.name}")
public class CashRegisterReceiver {
    /**
     * Receive method
     * @param in Some json object
     */
    @RabbitHandler
    public void receive(String in) {
        //
    }
}
