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
public class BranchService {

    private Dao<Branch> branchDao;

    public void save(Branch branch) {
        branchDao.save(branch);
    }

    public Optional<Branch> findById(Integer id) {
        return branchDao.findById(id);
    }

    public Optional<Branch> findByName(String name) {
        return branchDao.findByName(name);
    }

    public List<Branch> findAll() {
        return branchDao.findAll();
    }

    public void update(Integer id, Branch e) {
        branchDao.update(id,e);
    }

    public void delete(Integer id) {
        branchDao.delete(id);
    }

    public void deleteAll() {
        branchDao.deleteAll();
    }
}
