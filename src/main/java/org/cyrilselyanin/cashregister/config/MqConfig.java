package org.cyrilselyanin.cashregister.config;

import org.cyrilselyanin.cashregister.mq.CashRegisterReceiver;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Message queuing configuration
 */
@Configuration
public class MqConfig {
    /**
     * Direct exchange bean
     * @return instance of direct exchange
     */
    @Bean
    public DirectExchange direct() {
        return new DirectExchange("cashregister.direct");
    }

    /**
     * Configuration for queue, binding and receive instance
     */
    private static class ReceiveConfig {
        /**
         * Queue bean, without strict name (non-durable, exclusive, auto-delete).
         * Used with SPEL {autoDeletingQueue.name}
         * @return some queue
         */
        @Bean
        public Queue autoDeletingQueue() {
            return new AnonymousQueue();
        }

        /**
         * Binding for regcash routing key
         * @param directExchange an exchange (direct)
         * @param queue auto deleting queue
         * @return a configured binding
         */
        @Bean
        public Binding binding(DirectExchange directExchange, Queue queue) {
            return BindingBuilder.
                    bind(queue)
                    .to(directExchange)
                    .with("regcash");
        }

        /**
         * Bean for cash register receiver
         * @return cash register receiver instance
         */
        @Bean
        public CashRegisterReceiver receiver() {
            return new CashRegisterReceiver();
        }
    }
}
