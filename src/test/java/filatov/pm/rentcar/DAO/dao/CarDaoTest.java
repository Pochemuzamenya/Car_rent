package filatov.pm.rentcar.DAO.dao;

import filatov.pm.rentcar.DAO.dao.CarDao;
import filatov.pm.rentcar.DAO.dao.Dao;
import filatov.pm.rentcar.entity.Car;
import filatov.pm.rentcar.entity.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.reactive.mutiny.Mutiny;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class CarDaoTest {

    private static final Logger logger = LogManager.getLogger();

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("factory");

    private final Dao<Car> service = new CarDao(emf.unwrap(Mutiny.SessionFactory.class));

    Car car1 = new Car();
    Car car2 = new Car();
    Car car3 = new Car();

    @Before
    public void setUp() throws Exception {
        car1.setMark("Test mark");
        car1.setModel("Test model");
        car1.setCarBody("Test car body");
        car1.setStatus(Status.OK);
        car1.setRentalPrice(200D);
        car1.setReleaseDate(1993);

        car2.setMark("Test mark1");
        car2.setModel("Test model1");
        car2.setCarBody("Test car body1");
        car2.setStatus(Status.OK);
        car2.setRentalPrice(300D);
        car2.setReleaseDate(2000);

        car3.setMark("Test mark 2");
        car3.setModel("Test model 2");
        car3.setCarBody("Test car body 2");
        car3.setStatus(Status.NOT_OK);
        car3.setRentalPrice(500D);
        car3.setReleaseDate(2010);

        service.save(car1);
        service.save(car2);
    }

    @Test
    public void save() {

        service.save(car3);
        logger.info(car3 + " saved");
    }

    @Test
    public void findById() {
        Optional<Car> byId = service.findById(1);
        logger.info(byId.get());
        assert byId.get().equals(car1);
    }

    @Test
    public void findByName() {
        Optional<Car> test_model = service.findByName("Test model");
        assert test_model.isEmpty() || test_model.get().equals(car1);
        logger.info(test_model.get());
    }

    @Test
    public void findAll() {
        List<Car> all = service.findAll();
        logger.info(all);
    }

    @Test
    public void update() {
        service.update(car1.getId(), car2);
        Optional<Car> byId = service.findById(car1.getId());
        assert byId.isEmpty() || byId.get().equals(car2);
        logger.info(byId.get());
        logger.info(car2);
    }

    @Test
    public void delete() {
        service.delete(1);
        Optional<Car> byId = service.findById(1);
        assert byId.isEmpty();
    }

    @Test
    public void deleteAll() {
        service.deleteAll();
    }
}