package filatov.pm.rentcar.DAO;

import filatov.pm.rentcar.entity.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class EmployeeDaoTest {
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

    @After
    public void deleteAll() {
        service.deleteAll();
    }

    @Test
    public void save() {
        service.save(employee2);
    }



    @Test
    public void delete() {
        service.delete((Integer)1);
    }

    @Test
    public void findAll() {
        List<Employee> all = service.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void findById(){
        Optional<Employee> byId = service.findById((Integer) 1);
        System.out.println(byId);
    }
}