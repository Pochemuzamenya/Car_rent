package filatov.pm.rentcar.DAO;

import filatov.pm.rentcar.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    void save(T t);

    Optional<T> findById(Integer id);

    List<T> findAll();

    void update(T t, String[] params);

    void delete(Integer id);

    void deleteAll();
}
