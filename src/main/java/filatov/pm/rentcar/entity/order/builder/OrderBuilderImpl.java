package filatov.pm.rentcar.entity.order.builder;

import filatov.pm.rentcar.entity.Status;
import filatov.pm.rentcar.entity.car.Car;
import filatov.pm.rentcar.entity.customer.Customer;
import filatov.pm.rentcar.entity.employee.Employee;
import filatov.pm.rentcar.entity.order.Order;

public class OrderBuilderImpl implements OrderBuilder {

    private Order order;

    public OrderBuilderImpl() {
        super();
    }

    @Override
    public OrderBuilder reset() {
        order = new Order();
        return this;
    }

    @Override
    public OrderBuilder setupCar(Car car) {
        order.setCar(car);
        return this;
    }

    @Override
    public OrderBuilder setupGaveOutEmployee(Employee employee) {
        order.setGaveOutEmployee(employee);
        return this;
    }

    @Override
    public OrderBuilder setupCustomer(Customer customer) {
        order.setCustomer(customer);
        return this;
    }

    @Override
    public OrderBuilder setupState() {
        order.stateInProgress();
        return this;
    }

    @Override
    public OrderBuilder setupTitle() {
        order.setupTitle();
        return this;
    }

    @Override
    public OrderBuilder setupGaveOutDate() {
        order.setupGaveOutDate();
        return this;
    }

    @Override
    public OrderBuilder setupRefundDate() {
        order.setupRefundDate();
        return this;
    }


    @Override
    public OrderBuilder setupDeposit() {
        order.calculateDeposit();
        return this;
    }

    @Override
    public OrderBuilder setupCarStatus() {
        Status status = order.getCar().getStatus();
        order.setCarStatus(status);
        return this;
    }


    @Override
    public Order build() {
        return this.order;
    }
}
