package filatov.pm.rentcar.DAO.serviceImpl;

import filatov.pm.rentcar.DAO.dao.Dao;
import filatov.pm.rentcar.entity.Branch;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BranchService implements Dao<Branch> {

    Dao<Branch> branchDao;

    @Override
    public void save(Branch branch) {
        branchDao.save(branch);
    }

    @Override
    public Optional<Branch> findById(Integer id) {
        return branchDao.findById(id);
    }

    @Override
    public Optional<Branch> findByName(String name) {
        return branchDao.findByName(name);
    }

    @Override
    public List<Branch> findAll() {
        return branchDao.findAll();
    }

    @Override
    public void update(Branch branch, Branch e) {
        branchDao.update(branch,e);
    }

    @Override
    public void delete(Integer id) {
        branchDao.delete(id);
    }

    @Override
    public void deleteAll() {
        branchDao.deleteAll();
    }
}
