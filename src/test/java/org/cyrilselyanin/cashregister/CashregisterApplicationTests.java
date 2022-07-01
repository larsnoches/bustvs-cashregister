package org.cyrilselyanin.cashregister;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.test.context.SpringRabbitTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

//@SpringJunitConfig
@SpringRabbitTest
@SpringBootTest
class CashregisterApplicationTests {

	@Container
	public RabbitMQContainer container = new RabbitMQContainer("rabbitmq:management");

	@AutoWired
	public DirectExchange direct;

	@AutoWired
	public Queue autoDeletingQueue;

	@AutoWired
	public Binding binding;

	@AutoWired
	public CachingConnectionFactory connectionFactory;

	@AutoWired
	public RabbitTemplate rabbitTemplate;

	@AutoWired
	public CashRegisterReceiverService receiver;

	@TestConfiguration
	public static class RabbitConfig {
		@Bean
		public CachingConnectionFactory connectionFactory() {
			CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
			connectionFactory.setHost("localhost");
			connectionFactory.setPort("5672");
			connectionFactory.setUsername("guest");
			connectionFactory.setPassword("guest");
			return connectionFactory;
		}
	}

	@Test
	void sendMessage() {
		TicketDto ticketDto = new TicketDto(
			"Petrov",
			"Ivan",
			"Ivanovich",
			"105",
			"Center",
			null,
			500
		);

		rabbitTemplate.convertAndSend(
			direct.getName(),
			"regcash",
			ticketDto
		);
	}

}
