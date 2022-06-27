package filatov.pm.rentcar.DAO.serviceImpl.reactive;

import filatov.pm.rentcar.DAO.dao.Dao;
import filatov.pm.rentcar.entity.employee.Employee;
import org.springframework.stereotype.Component;

@Component
public class ReactiveEmployeeService extends AbstractReactiveService<Employee> implements ReactiveService<Employee> {
    public ReactiveEmployeeService(Dao<Employee> employeeDao) {
        super(employeeDao);
    }
}
