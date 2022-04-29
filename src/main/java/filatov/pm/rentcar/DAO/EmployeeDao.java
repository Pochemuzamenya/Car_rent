package filatov.pm.rentcar.DAO;

import filatov.pm.rentcar.entity.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.reactive.mutiny.Mutiny;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class EmployeeDao implements Dao<Employee> {

    private static final Logger log = LogManager.getLogger(EmployeeDao.class);
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("factory");
    Mutiny.SessionFactory sessionFactory = emf.unwrap(Mutiny.SessionFactory.class);

    @Override
    public void save(Employee employee) {
        sessionFactory.withTransaction(
                        session -> session.persist(employee))
                .await().indefinitely();
        log.trace(employee + " saved");
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return Optional.ofNullable(sessionFactory.withTransaction(
                session -> session.createQuery("FROM Employee e WHERE e.id = :id", Employee.class)
                        .setParameter("id", id)
                        .getSingleResultOrNull()
        ).await().indefinitely());
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return Optional.ofNullable(sessionFactory.withTransaction(
                session -> session.createQuery("FROM Employee e WHERE e.name =:name", Employee.class)
                        .setParameter("name", name)
                        .getSingleResultOrNull()
        ).await().indefinitely());
    }

    @Override
    public List<Employee> findAll() {
        return sessionFactory.withTransaction(
                session -> session.createQuery("SELECT e FROM Employee e", Employee.class)
                        .getResultList())
                .await().indefinitely();
    }

    @Override
    public void update(Employee employee, Employee updateEmployee) {
        sessionFactory.withTransaction(
                session -> session.find(Employee.class, employee.getId())
                        .invoke(e -> {
                            e.setLogin(updateEmployee.getLogin());
                            e.setName(updateEmployee.getName());
                            e.setPassword(updateEmployee.getPassword());
                        })
        ).await().indefinitely();
    }

    @Override
    public void delete(Integer id) {
        sessionFactory.withTransaction(
                session -> session.find(Employee.class, id)
                        .chain(session::remove)
        ).await().indefinitely();
        log.trace("deleted");
    }

    @Override
    public void deleteAll() {
        sessionFactory.withTransaction(
                        (session, tx) -> session.createQuery("delete Employee").executeUpdate())
                .await().indefinitely();
    }
}
