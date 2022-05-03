package filatov.pm.rentcar.DAO.serviceImpl;

import filatov.pm.rentcar.DAO.dao.Dao;
import filatov.pm.rentcar.entity.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService implements Dao<Customer> {

    Dao<Customer> customerDao;

    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return customerDao.findById(id);
    }

    @Override
    public Optional<Customer> findByName(String name) {
        return customerDao.findByName(name);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public void update(Customer customer, Customer e) {
        customerDao.update(customer,e);
    }

    @Override
    public void delete(Integer id) {
        customerDao.delete(id);
    }

    @Override
    public void deleteAll() {
        customerDao.deleteAll();
    }
}
