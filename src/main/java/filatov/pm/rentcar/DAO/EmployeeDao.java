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
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public void update(Employee employee, String[] params) {

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

    //TODO:
    // delete(not working now),
    // update,
    // findAll,
    // findById
}
