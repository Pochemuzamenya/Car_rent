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
    Employee employee3 = new Employee();

    @Before
    public void init(){
        employee1.setLogin("login");
        employee1.setName("name");
        employee1.setPassword("password");
        employee2.setLogin("emp2");
        employee2.setName("pepega");
        employee2.setPassword("pepegalul");
        employee3.setLogin("testlogin");
        employee3.setName("testname");
        employee3.setPassword("testpassword");
        service.save(employee1);
        service.save(employee2);
    }

    @Test
    public void save() {
        service.save(employee3);
        Optional<Employee> byName = service.findByName(employee3.getName());
        assert byName.isPresent() && byName.get().equals(employee3);
    }

    @Test
    public void findByName(){
        Optional<Employee> pepega = service.findByName("pepega");
        assert pepega.isPresent() && pepega.get().getName().equals("pepega");
    }

    @Test
    public void delete() {
        service.delete(1);
        Optional<Employee> byId = service.findById(1);
        assert byId.isEmpty();
    }

    @Test
    public void findAll() {
        List<Employee> all = service.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void update() {
        service.update(employee1, employee2);
        Optional<Employee> byId = service.findById(employee1.getId());
        assert byId.isEmpty() || byId.get().equals(employee2);
        System.out.println(byId.get());
    }
    @Test
    public void findById(){
        Optional<Employee> byId = service.findById(2);
        assert byId.isPresent() && byId.get().equals(employee2);
    }
}