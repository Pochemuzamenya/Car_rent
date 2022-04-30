package filatov.pm.rentcar;

import filatov.pm.rentcar.DAO.EmployeeDaoImpl;
import org.hibernate.reactive.mutiny.Mutiny;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RentcarApplicationTests {

	@Autowired
	Mutiny.SessionFactory sessionFactory;

	@Autowired
	EmployeeDaoImpl employeeDao;

	@Test
	void contextLoads() {
		assertThat(sessionFactory).isNotNull();
		assertThat(employeeDao).isNotNull();
	}
}
