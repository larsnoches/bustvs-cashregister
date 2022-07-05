package org.cyrilselyanin.cashregister;

import org.cyrilselyanin.cashregister.dto.TicketDto;
import org.cyrilselyanin.cashregister.dto.TokenRequestDto;
import org.cyrilselyanin.cashregister.service.CashRegisterReceiverService;
import org.cyrilselyanin.cashregister.service.SbisServiceImpl;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.amqp.rabbit.test.RabbitListenerTestHarness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Primary;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.annotation.PostConstruct;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

//@SpringJunitConfig
// @SpringRabbi
// tTest

@SpringBootTest
//@ExtendWith(OutputCaptureExtension.class)
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

//	@MockBean
//	public SbisServiceImpl sbisService;

	@MockBean
	public CashRegisterReceiverService receiverService;

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
	void sendMessage() throws IOException {
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

//		doNothing().when(receiverService).receive(any());
		doAnswer(i -> {
			System.out.println("wow");
			return null;
		}).when(receiverService).receive(any());

	}
	

}
