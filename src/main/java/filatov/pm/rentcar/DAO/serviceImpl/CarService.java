package filatov.pm.rentcar.DAO.serviceImpl;

import filatov.pm.rentcar.DAO.dao.Dao;
import filatov.pm.rentcar.entity.car.Car;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarService {

    private Dao<Car> carDao;

    public void save(Car car) {
        carDao.save(car);
    }

    public Optional<Car> findById(Integer id) {
        return carDao.findById(id);
    }

    public Optional<Car> findByName(String name) {
        return carDao.findByName(name);
    }

    public List<Car> findAll() {
        return carDao.findAll();
    }

    public void update(Integer id, Car e) {
        carDao.update(id, e);
    }

    public void delete(Integer id) {
        carDao.delete(id);
    }

    public void deleteAll() {
        carDao.deleteAll();
    }
}
