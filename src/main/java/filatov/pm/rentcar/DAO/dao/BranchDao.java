package filatov.pm.rentcar.DAO.dao;

import filatov.pm.rentcar.entity.Branch.Branch;
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
public class BranchDao implements Dao<Branch>{

    private static final Logger log = LogManager.getLogger();

    Mutiny.SessionFactory sessionFactory;

    @Override
    public void save(Branch branch) {
        sessionFactory.withTransaction(
                session -> session.persist(branch)
        ).await().indefinitely();
        log.info(branch + " saved");
    }

    @Override
    public Optional<Branch> findById(Integer id) {
        return Optional.ofNullable(sessionFactory.withTransaction(
                session -> session.createQuery("FROM Branch b WHERE b.id =:id", Branch.class)
                        .setParameter("id", id)
                        .getSingleResultOrNull()
        ).await().indefinitely());
    }

    @Override
    public Optional<Branch> findByName(String name) {
        return Optional.ofNullable(sessionFactory.withTransaction(
                session -> session.createQuery("FROM Branch b WHERE b.title =:name", Branch.class)
                        .setParameter("name", name)
                        .getSingleResultOrNull()
        ).await().indefinitely());
    }

    @Override
    public List<Branch> findAll() {
        return sessionFactory.withTransaction(
                session -> session.createQuery("SELECT b FROM Branch b", Branch.class)
                        .getResultList()
        ).await().indefinitely();
    }

    @Override
    public void update(Integer id, Branch branch) {

    }

    @Override
    public void delete(Integer id) {
        sessionFactory.withTransaction(
                session -> session.find(Branch.class, id)
                        .chain(session::remove)
        ).await().indefinitely();
        log.info("deleted");
    }

    @Override
    public void deleteAll() {
        sessionFactory.withTransaction(
                session -> session.createQuery("delete Branch").executeUpdate()
        ).await().indefinitely();
    }
    // TODO: 03.05.2022 update
}
