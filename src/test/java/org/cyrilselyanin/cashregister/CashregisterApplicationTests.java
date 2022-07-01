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

//@SpringJunitConfig
@SpringRabbitTest
@SpringBootTest
class CashregisterApplicationTests {

	@Container
	public RabbitMQContainer container = new RabbitMQContainer("rabbitmq:management");

	@TestConfiguration
	public static class RabbitConfig {
		@Bean
		public
	}

	@Test
	void contextLoads() {
	}

}
