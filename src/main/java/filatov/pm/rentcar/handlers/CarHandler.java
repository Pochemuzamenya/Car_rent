package filatov.pm.rentcar.handlers;

import filatov.pm.rentcar.DAO.serviceImpl.reactive.ReactiveService;
import filatov.pm.rentcar.entity.car.Car;
import org.springframework.stereotype.Component;

@Component
public class CarHandler extends AbstractHandler<Car> implements Handler{
    public CarHandler(ReactiveService<Car> service) {
        super(service);
    }
}
