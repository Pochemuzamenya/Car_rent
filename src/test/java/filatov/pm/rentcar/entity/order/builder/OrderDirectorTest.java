package filatov.pm.rentcar.entity.order.builder;

import filatov.pm.rentcar.entity.Branch.Branch;
import filatov.pm.rentcar.entity.car.Car;
import filatov.pm.rentcar.entity.customer.Customer;
import filatov.pm.rentcar.entity.employee.Employee;
import filatov.pm.rentcar.entity.order.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;


class OrderDirectorTest {
    private static final Logger log = LogManager.getLogger();

    OrderBuilder builder = new OrderBuilderImpl();

    Car car = new Car();

    Employee employee = new Employee();

    Branch branch = new Branch();

    Customer customer = new Customer();

    @Test
    void buildOrder() {
        OrderDirector director = new OrderDirector(builder);
        Order order = director.buildOrder(car, employee, customer);
        log.info(order + "\nCreated");
    }
}