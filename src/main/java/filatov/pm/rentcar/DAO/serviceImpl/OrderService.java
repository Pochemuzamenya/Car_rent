package filatov.pm.rentcar.DAO.serviceImpl;

import filatov.pm.rentcar.DAO.dao.Dao;
import filatov.pm.rentcar.entity.Order;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderService implements Dao<Order> {

    Dao<Order> orderDao;

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderDao.findById(id);
    }

    @Override
    public Optional<Order> findByName(String name) {
        return orderDao.findByName(name);
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public void update(Order order, Order e) {
        orderDao.update(order, e);
    }

    @Override
    public void delete(Integer id) {
        orderDao.delete(id);
    }

    @Override
    public void deleteAll() {
        orderDao.deleteAll();
    }
}
