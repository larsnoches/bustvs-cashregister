package org.cyrilselyanin.cashregister;

import org.cyrilselyanin.cashregister.dto.TicketDto;
import org.cyrilselyanin.cashregister.service.CashRegisterReceiverService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.Duration;

//@SpringJunitConfig
// @SpringRabbi
// tTest

@SpringBootTest
class CashregisterApplicationTests {
	@Autowired
	public DirectExchange direct;

	@Autowired
	public Queue autoDeletingQueue;

	@Autowired
	public Binding binding;

	@Autowired
	public CachingConnectionFactory connectionFactory;

	@Autowired
	public RabbitTemplate rabbitTemplate;

	@Autowired
	public CashRegisterReceiverService receiver;

	@TestConfiguration
	public static class RabbitConfig {
		@Bean
		public CachingConnectionFactory connectionFactory() {
			CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
			connectionFactory.setHost("localhost");
			connectionFactory.setPort(5672);
			connectionFactory.setUsername("guest");
			connectionFactory.setPassword("guest");
			return connectionFactory;
		}
	}

	@Test
	void sendMessage() {
//		container.start();

		TicketDto ticketDto = new TicketDto(
			"Petrov",
			"Ivan",
			"Ivanovich",
			"105",
			"Center",
			null,
				BigDecimal.valueOf(500)
		);

		rabbitTemplate.convertAndSend(
			direct.getName(),
			"regcash",
			ticketDto
		);

		
	}
	

}
