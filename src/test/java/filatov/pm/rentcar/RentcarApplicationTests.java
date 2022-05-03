package filatov.pm.rentcar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.reactive.mutiny.Mutiny;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RentcarApplicationTests {

	private static final Logger log = LogManager.getLogger();

	@Autowired
	Mutiny.SessionFactory sessionFactory;

	@Test
	void contextLoads() {
		assertThat(sessionFactory).isNotNull();
	}
}
