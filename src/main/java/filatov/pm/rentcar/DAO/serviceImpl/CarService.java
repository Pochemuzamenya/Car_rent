package filatov.pm.rentcar.DAO.serviceImpl;

import filatov.pm.rentcar.DAO.dao.Dao;
import filatov.pm.rentcar.entity.Car;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CarService implements Dao<Car> {

    Dao<Car> carDao;

    @Override
    public void save(Car car) {
        carDao.save(car);
    }

    @Override
    public Optional<Car> findById(Integer id) {
        return carDao.findById(id);
    }

    @Override
    public Optional<Car> findByName(String name) {
        return carDao.findByName(name);
    }

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public void update(Car car, Car e) {
        carDao.update(car, e);
    }

    @Override
    public void delete(Integer id) {
        carDao.delete(id);
    }

    @Override
    public void deleteAll() {
        carDao.deleteAll();
    }
}
