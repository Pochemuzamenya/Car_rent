package filatov.pm.rentcar.DAO.dao;

import filatov.pm.rentcar.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.reactive.mutiny.Mutiny;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class CustomerDaoTest {

    private static final Logger logger = LogManager.getLogger();

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("factory");

    private final Dao<Customer> service = new CustomerDao(emf.unwrap(Mutiny.SessionFactory.class));

    Customer customer = new Customer();
    Order order = new Order();

    @Before
    public void init() {
        order.setPayment(200D);
        customer.setBranches(Set.of(new Branch()));
        customer.addOrder(order);
        customer.setState(CustomerState.OK);
        customer.setName("Test Name");

        service.save(customer);
    }

    @Test
    public void save() {
        Customer customer1 = new Customer();
        customer1.addBranch(new Branch());
        customer1.addOrder(new Order());
        customer1.setState(CustomerState.NOT_OK);
        customer1.setName("Customer name");
        service.save(customer1);
        Optional<Customer> byName = service.findById(2);
        assert byName.isPresent() && byName.get().getName().equals(customer1.getName());
    }

    @Test
    public void findById() {
        Optional<Customer> byId = service.findById(1);

        logger.info(byId.get().getBranches());

    }

    @Test
    public void findByName() {
        String param = "Test Name";
        Optional<Customer> test_name = service.findByName(param);
        assert test_name.isPresent() && test_name.get().getName().equals(param);
        logger.info(test_name.get().getOrders());
    }

    @Test
    public void findAll() {
        List<Customer> all = service.findAll();
        all.forEach(System.out::println);
    }

  /*  @Test
    public void update() {
    }
*/
    @Test
    public void delete() {
        service.delete(1);
        Optional<Customer> byId = service.findById(1);
        assert byId.isEmpty();
    }
}