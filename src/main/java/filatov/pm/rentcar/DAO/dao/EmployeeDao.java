package filatov.pm.rentcar.DAO.dao;

import filatov.pm.rentcar.DAO.dao.Dao;
import filatov.pm.rentcar.entity.Employee;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeDao implements Dao<Employee> {

    private static final Logger log = LogManager.getLogger();

    Mutiny.SessionFactory sessionFactory;

    @Override
    public void save(Employee employee) {
        sessionFactory.withTransaction(
                        session -> session.persist(employee))
                .await().indefinitely();
        log.info(employee + " saved");
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return Optional.ofNullable(sessionFactory.withTransaction(
                session -> session.createQuery("FROM Employee e WHERE e.id =:id", Employee.class)
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
    public void update(Integer id, Employee employee) {
        sessionFactory.withTransaction(
                session -> session.find(Employee.class, id)
                        .invoke(e -> {
                            e.setLogin(employee.getLogin());
                            e.setName(employee.getName());
                            e.setPassword(employee.getPassword());
                        })
        ).await().indefinitely();
    }

    @Override
    public void delete(Integer id) {
        sessionFactory.withTransaction(
                session -> session.find(Employee.class, id)
                        .chain(session::remove)
        ).await().indefinitely();
        log.info("Employee with id: " + id + " deleted");
    }

    @Override
    public void deleteAll() {
        sessionFactory.withTransaction(
                        (session, tx) -> session.createQuery("delete Employee").executeUpdate())
                .await().indefinitely();
    }
}
