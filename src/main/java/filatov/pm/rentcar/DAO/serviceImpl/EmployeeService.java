package filatov.pm.rentcar.DAO.serviceImpl;

import filatov.pm.rentcar.DAO.dao.Dao;
import filatov.pm.rentcar.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class EmployeeService {

    private Dao<Employee> employeeDao;

    public Mono<Employee> save(Employee employee) {
        employeeDao.save(employee);
        return Mono.just(employee);
    }

    public Mono<Employee> findById(Integer id) {
        return  Mono.just(employeeDao.findById(id).orElseThrow());
    }

    public Mono<Employee> findByName(String name) {
        return Mono.just(employeeDao.findByName(name).orElseThrow());
    }

    public Flux<Employee> findAll() {
        return Flux.fromIterable(employeeDao.findAll());
    }

    public void update(Employee employee, Employee e) {
        employeeDao.update(employee, e);
    }

    public Mono<Employee> delete(Integer id) {
        employeeDao.delete(id);
        return Mono.empty();
    }

    public void deleteAll() {
        employeeDao.deleteAll();
    }

    // TODO: 08.05.2022 refactor update
}
