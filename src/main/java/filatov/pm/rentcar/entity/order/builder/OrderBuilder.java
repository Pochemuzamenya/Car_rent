package filatov.pm.rentcar.entity.order.builder;

import filatov.pm.rentcar.entity.car.Car;
import filatov.pm.rentcar.entity.customer.Customer;
import filatov.pm.rentcar.entity.employee.Employee;
import filatov.pm.rentcar.entity.order.Order;

public interface OrderBuilder {

    OrderBuilder reset();

    OrderBuilder setupCar(Car car);

    OrderBuilder setupGaveOutEmployee(Employee employee);

    OrderBuilder setupCustomer(Customer customer);

    OrderBuilder setupState();

    OrderBuilder setupTitle();

    OrderBuilder setupGaveOutDate();

    OrderBuilder setupRefundDate();

    OrderBuilder setupDeposit();

    OrderBuilder setupCarStatus();

    Order build();
}
