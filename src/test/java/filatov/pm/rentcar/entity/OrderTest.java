package filatov.pm.rentcar.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {

    private static final Logger log = LogManager.getLogger();

    Order order = new Order();
    Car car = new Car();
    Employee employee1 = new Employee();
    Employee employee2 = new Employee();
    Customer customer = new Customer();
    Branch branch = new Branch();

    @Before
    public void setUp() throws Exception {
        car.setState(CarState.VACANT);
        car.setCarBody("Седан");
        car.setReleaseDate(2000);
        car.setModel("Camry");
        car.setMark("Toyota");
        car.setRentalPrice(2000D);
        car.setStatus(Status.OK);

        employee1.setName("Олег");
        employee2.setName("Андрей");

        customer.setName("Вован");
        customer.setState(CustomerState.OK);
        customer.addOrder(order);
        customer.addBranch(branch);

        branch.setBalance(0D);
        branch.setTitle("Rent a car");
        branch.setCity(City.Irkutsk);
        branch.addCar(car);
        branch.addStaff(employee1);
        branch.addStaff(employee2);
        branch.addOrder(order);

        order.setCar(car);
        order.setGaveOutEmployee(employee1);
        order.setCustomer(customer);
        order.setCarStatus(car.getStatus());
        order.stateInProgress();
        order.setupGaveOutDate();
        order.setupRefundDate();
        order.calculateDeposit();
        order.setupFactRefundDate();
        order.setupTitle();
    }

    @Test
    public void test() {
        log.info(order.getTitle());
    }

    @After
    public void tearDown() throws Exception {
    }
}