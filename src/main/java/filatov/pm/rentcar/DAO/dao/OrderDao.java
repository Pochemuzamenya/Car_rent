package filatov.pm.rentcar.DAO.dao;

import filatov.pm.rentcar.entity.Customer;
import filatov.pm.rentcar.entity.Order;
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
public class OrderDao implements Dao<Order>{

    private static final Logger log = LogManager.getLogger();

    Mutiny.SessionFactory sessionFactory;

    @Override
    public void save(Order order) {
        sessionFactory.withTransaction(
                session -> session.persist(order)
        ).await().indefinitely();
        log.info(order + " saved");
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return Optional.ofNullable(sessionFactory.withTransaction(
                session -> session.createQuery("FROM Order o WHERE o.id =:id", Order.class)
                        .setParameter("id", id)
                        .getSingleResultOrNull()
        ).await().indefinitely());
    }

    @Override
    public Optional<Order> findByName(String name) {
        return Optional.ofNullable(sessionFactory.withTransaction(
                session -> session.createQuery("FROM Order o WHERE o.title =:name", Order.class)
                        .setParameter("name", name)
                        .getSingleResultOrNull()
        ).await().indefinitely());
    }

    @Override
    public List<Order> findAll() {
        return sessionFactory.withTransaction(
                session -> session.createQuery("SELECT o FROM Order o", Order.class)
                        .getResultList()
        ).await().indefinitely();
    }

    @Override
    public void update(Order order, Order e) {

    }

    @Override
    public void delete(Integer id) {
        sessionFactory.withTransaction(
                session -> session.find(Order.class, id)
                        .chain(session::remove)
        ).await().indefinitely();
        log.info("deleted");
    }

    @Override
    public void deleteAll() {
        sessionFactory.withTransaction(
                session -> session.createQuery("delete Order").executeUpdate()
        ).await().indefinitely();
    }
}
