package filatov.pm.rentcar.DAO.dao;

import filatov.pm.rentcar.DAO.dao.Dao;
import filatov.pm.rentcar.entity.Car;
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
public class CarDao implements Dao<Car> {

    private static final Logger log = LogManager.getLogger();

    Mutiny.SessionFactory sessionFactory;

    @Override
    public void save(Car car) {
        sessionFactory.withTransaction(
                        session -> session.persist(car))
                .await().indefinitely();
        log.trace(car + " saved");
    }

    @Override
    public Optional<Car> findById(Integer id) {
        return Optional.ofNullable(sessionFactory.withTransaction(
                session -> session.createQuery("FROM Car c WHERE c.id =:id", Car.class)
                        .setParameter("id", id)
                        .getSingleResultOrNull()
        ).await().indefinitely());
    }

    @Override
    public Optional<Car> findByName(String name) {
        return Optional.ofNullable(sessionFactory.withTransaction(
                session -> session.createQuery("FROM Car c WHERE c.model =:name", Car.class)
                        .setParameter("name", name)
                        .getSingleResultOrNull()
        ).await().indefinitely());
    }

    @Override
    public List<Car> findAll() {
        return sessionFactory.withTransaction(
                        session -> session.createQuery("SELECT c FROM Car c", Car.class)
                                .getResultList())
                .await().indefinitely();
    }

    @Override
    public void update(Car car, Car e) {
        sessionFactory.withTransaction(
                session -> session.find(Car.class, car.getId())
                        .invoke(c -> {
                            c.setCarBody(e.getCarBody());
                            c.setMark(e.getMark());
                            c.setModel(e.getModel());
                            c.setReleaseDate(e.getReleaseDate());
                            c.setStatus(e.getStatus());
                            c.setRentalPrice(e.getRentalPrice());
                            c.setState(e.getState());
                        })
        ).await().indefinitely();
    }

    @Override
    public void delete(Integer id) {
        sessionFactory.withTransaction(
                session -> session.find(Car.class, id)
                        .chain(session::remove)
        ).await().indefinitely();
        log.trace("deleted");
    }

    @Override
    public void deleteAll() {
        sessionFactory.withTransaction(
                        (session, tx) -> session.createQuery("delete Car").executeUpdate())
                .await().indefinitely();
    }
}
