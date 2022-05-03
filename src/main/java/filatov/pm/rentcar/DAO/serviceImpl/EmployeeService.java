package filatov.pm.rentcar.DAO.serviceImpl;

import filatov.pm.rentcar.DAO.dao.Dao;
import filatov.pm.rentcar.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeService implements Dao<Employee> {

    Dao<Employee> employeeDao;

    @Override
    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return employeeDao.findById(id);
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return employeeDao.findByName(name);
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public void update(Employee employee, Employee e) {
        employeeDao.update(employee, e);
    }

    @Override
    public void delete(Integer id) {
        employeeDao.delete(id);
    }

    @Override
    public void deleteAll() {
        employeeDao.deleteAll();
    }
}
