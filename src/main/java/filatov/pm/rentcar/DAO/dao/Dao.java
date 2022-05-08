package filatov.pm.rentcar.DAO.dao;

import java.util.List;
import java.util.Optional;


public interface Dao<T> {

    void save(T t);

    Optional<T> findById(Integer id);

    Optional<T> findByName(String name);

    List<T> findAll();

    void update(T t, T e);

    void delete(Integer id);

    void deleteAll();

    // TODO: 04.05.2022 perform a refactor 'update'
}
