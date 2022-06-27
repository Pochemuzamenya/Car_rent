package filatov.pm.rentcar.DAO.serviceImpl;

import filatov.pm.rentcar.DAO.dao.Dao;
import filatov.pm.rentcar.entity.customer.Customer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class CustomerService {

    private Dao<Customer> customerDao;

    public Mono<Customer> save(Customer customer) {
        customerDao.save(customer);
        return Mono.just(customer);
    }

    public Optional<Customer> findById(Integer id) {
        return customerDao.findById(id);
    }

    public Optional<Customer> findByName(String name) {
        return customerDao.findByName(name);
    }

    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    public void update(Integer id, Customer e) {
        customerDao.update(id,e);
    }

    public void delete(Integer id) {
        customerDao.delete(id);
    }

    public void deleteAll() {
        customerDao.deleteAll();
    }
}
