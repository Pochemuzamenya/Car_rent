package filatov.pm.rentcar.entity.order.builder;

import filatov.pm.rentcar.entity.car.Car;
import filatov.pm.rentcar.entity.customer.Customer;
import filatov.pm.rentcar.entity.employee.Employee;
import filatov.pm.rentcar.entity.order.Order;

public class OrderDirector {
    private OrderBuilder builder;

    public OrderDirector(OrderBuilder builder) {
        this.builder = builder;
    }

    public Order buildOrder(Car car, Employee employee, Customer customer) {
        return builder
                .reset()
                .setupState()
                .setupGaveOutEmployee(employee)
                .setupCar(car)
                .setupCustomer(customer)
                .setupCarStatus()
                .setupGaveOutDate()
                .setupRefundDate()
                .setupDeposit()
                .setupTitle()
                .build();
    }
}
