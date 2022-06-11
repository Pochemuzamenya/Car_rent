package filatov.pm.rentcar.DAO.dao;

import filatov.pm.rentcar.entity.Customer;
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
public class CustomerDao implements Dao<Customer>{

    private static final Logger log = LogManager.getLogger();

    Mutiny.SessionFactory sessionFactory;

    @Override
    public void save(Customer customer) {
        sessionFactory.withTransaction(
                session -> session.persist(customer)
        ).await().indefinitely();
        log.info(customer + " saved");
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return Optional.ofNullable(sessionFactory.withTransaction(
                session -> session.createQuery("FROM Customer c JOIN FETCH c.orders WHERE c.id =:id", Customer.class)
                        .setParameter("id", id)
                        .getSingleResultOrNull()
        ).await().indefinitely());
    }

    @Override
    public Optional<Customer> findByName(String name) {
        return Optional.ofNullable(sessionFactory.withTransaction(
                session -> session.createQuery("FROM Customer c JOIN FETCH c.orders WHERE c.name =:name", Customer.class)
                        .setParameter("name", name)
                        .getSingleResultOrNull()
        ).await().indefinitely());
    }

    @Override
    public List<Customer> findAll() {
        return sessionFactory.withTransaction(
                        session -> session.createQuery("SELECT c FROM Customer c", Customer.class)
                                .getResultList()
                ).await().indefinitely();
    }

    @Override
    public void update(Integer id, Customer customer) {
        sessionFactory.withTransaction(
                session -> session.find(Customer.class, id)
                        .invoke(c -> {
                            c.setName(customer.getName());
                            c.setState(customer.getState());
                            c.setOrders(customer.getOrders());
                            c.setBranches(customer.getBranches());
                        })
        ).await().indefinitely();
    }

    @Override
    public void delete(Integer id) {
        sessionFactory.withTransaction(
                session -> session.find(Customer.class, id)
                        .chain(session::remove)
        ).await().indefinitely();
        log.info("deleted");
    }

    @Override
    public void deleteAll() {
        sessionFactory.withTransaction(
                session -> session.createQuery("delete Customer").executeUpdate()
        ).await().indefinitely();
    }
}
