package filatov.pm.rentcar;

import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
public class RentcarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentcarApplication.class, args);
	}

	@Bean(name = "sessionFactory")
	public Mutiny.SessionFactory getSession(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("factory");
		return emf.unwrap(Mutiny.SessionFactory.class);
	}
}
