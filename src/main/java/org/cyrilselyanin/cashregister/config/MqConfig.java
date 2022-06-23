package org.cyrilselyanin.cashregister.config;

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
     * @return
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
         * Queue bean, without strict name.
         * Used with SPEL {autoDeletingQueue.name}
         * @return
         */
        @Bean
        public Queue autoDeletingQueue() {
            return new AnonymousQueue();
        }

        /**
         * Binding for regcash routing key
         * @param directExchange
         * @param queue
         * @return
         */
        @Bean
        public Binding binding(DirectExchange directExchange, Queue queue) {
            return BindingBuilder.
                    bind(queue)
                    .to(directExchange)
                    .with("regcash");
        }
    }
}
