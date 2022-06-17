package filatov.pm.rentcar.handlers;

import filatov.pm.rentcar.DAO.serviceImpl.reactive.ReactiveService;
import filatov.pm.rentcar.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerHandler extends AbstractHandler<Customer> implements Handler{
    public CustomerHandler(ReactiveService<Customer> service) {
        super(service);
    }

}
