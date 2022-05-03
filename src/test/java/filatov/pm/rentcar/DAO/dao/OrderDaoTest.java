package filatov.pm.rentcar.DAO.dao;

import filatov.pm.rentcar.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.reactive.mutiny.Mutiny;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class OrderDaoTest {
    private static final Logger log = LogManager.getLogger();

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("factory");

    private final Dao<Order> orderService = new OrderDao(emf.unwrap(Mutiny.SessionFactory.class));
    Dao<Employee> empService = new EmployeeDao(emf.unwrap(Mutiny.SessionFactory.class));
    Dao<Car> carService = new CarDao(emf.unwrap(Mutiny.SessionFactory.class));
    Dao<Customer> customerService = new CustomerDao(emf.unwrap(Mutiny.SessionFactory.class));

    Car car = new Car();
    Customer customer = new Customer();
    Employee employee = new Employee();


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void save() {
        car.setMark("Test mark");
        car.setModel("Test model");
        car.setCarBody("Test car body");
        car.setStatus(Status.OK);
        car.setRentalPrice(200D);
        car.setReleaseDate(1993);
        carService.save(car);

        customer.setBranches(Set.of());
        customer.setOrders(Set.of());
        customer.setState(CustomerState.OK);
        customer.setName("Test Name");
        customerService.save(customer);

        employee.setPassword("test password");
        employee.setLogin("test login");
        employee.setName("test name");
        empService.save(employee);
        Car car1 = carService.findById(1).get();
        Customer customer1 = customerService.findById(1).get();
        Employee employee1 = empService.findById(1).get();
        log.info(car1);
        log.info(customer1);
        log.info(employee1);

        Order order1 = new Order(car1, employee1,customer1,new Branch(), employee1);
        log.info(order1);
//
//        orderService.save(order1);
//        log.info(order1 + " saved");
    }

    @Test
    public void findById() {
    }

    @Test
    public void findByName() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void deleteAll() {
    }
}