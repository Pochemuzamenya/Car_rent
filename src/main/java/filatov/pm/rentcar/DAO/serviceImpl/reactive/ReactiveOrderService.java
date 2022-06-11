package filatov.pm.rentcar.DAO.serviceImpl.reactive;

import filatov.pm.rentcar.DAO.dao.Dao;
import filatov.pm.rentcar.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class ReactiveOrderService extends AbstractReactiveService<Order> implements ReactiveService<Order> {
    public ReactiveOrderService(Dao<Order> dao) {
        super(dao);
    }
}
