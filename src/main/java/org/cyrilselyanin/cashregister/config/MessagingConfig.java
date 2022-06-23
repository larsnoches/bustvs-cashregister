package org.cyrilselyanin.cashregister.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    @Bean
    public DirectExchange direct() {
        return new DirectExchange("cashregister.direct");
    }

    private static class ReceiveConfig {
        @Bean
        public Queue autoDeletingQueue() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding(DirectExchange directExchange, Queue queue) {
            return BindingBuilder.
                    bind(queue)
                    .to(directExchange)
                    .with("regcash");
        }
    }
}
