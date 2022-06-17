package filatov.pm.rentcar.handlers;

import filatov.pm.rentcar.DAO.serviceImpl.reactive.ReactiveService;
import filatov.pm.rentcar.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderHandler extends AbstractHandler<Order> implements Handler{
    public OrderHandler(ReactiveService<Order> service) {
        super(service);
    }
}
