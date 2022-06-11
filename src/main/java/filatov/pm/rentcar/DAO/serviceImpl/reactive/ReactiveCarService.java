package filatov.pm.rentcar.DAO.serviceImpl.reactive;

import filatov.pm.rentcar.DAO.dao.Dao;
import filatov.pm.rentcar.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class ReactiveCarService extends AbstractReactiveService<Car> implements ReactiveService<Car> {
    public ReactiveCarService(Dao<Car> dao) {
        super(dao);
    }
}
