package filatov.pm.rentcar.DAO.serviceImpl.reactive;

import filatov.pm.rentcar.DAO.dao.Dao;
import filatov.pm.rentcar.entity.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class ReactiveCustomerService extends AbstractReactiveService<Customer> implements ReactiveService<Customer> {
    public ReactiveCustomerService(Dao<Customer> dao) {
        super(dao);
    }
}
