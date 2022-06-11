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
public class OrderService {

    private Dao<Order> orderDao;

    public void save(Order order) {
        orderDao.save(order);
    }

    public Optional<Order> findById(Integer id) {
        return orderDao.findById(id);
    }

    public Optional<Order> findByName(String name) {
        return orderDao.findByName(name);
    }

    public List<Order> findAll() {
        return orderDao.findAll();
    }

    public void update(Integer id, Order e) {
        orderDao.update(id, e);
    }

    public void delete(Integer id) {
        orderDao.delete(id);
    }

    public void deleteAll() {
        orderDao.deleteAll();
    }
}
