package filatov.pm.rentcar.DAO;

import filatov.pm.rentcar.entity.Employee;
import org.junit.Before;
import org.junit.Test;

public class DBServiceDAOTest {
    Dao<Employee> service = new EmployeeDao();
    Employee employee1 = new Employee();
    Employee employee2 = new Employee();

    @Before
    public void init(){
        employee1.setLogin("login");
        employee1.setName("name");
        employee1.setPassword("password");
        employee2.setLogin("emp2");
        employee2.setName("pepega");
        employee2.setPassword("pepegalul");
        service.save(employee1);
    }

    @Test
    public void save() {
        service.save(employee2);
    }

    @Test
    public void delete() {
        service.deleteAll();
    }
}