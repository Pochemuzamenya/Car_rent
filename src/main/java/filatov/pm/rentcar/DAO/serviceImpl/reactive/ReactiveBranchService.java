package filatov.pm.rentcar.DAO.serviceImpl.reactive;

import filatov.pm.rentcar.DAO.dao.Dao;
import filatov.pm.rentcar.entity.Branch;
import org.springframework.stereotype.Component;

@Component
public class ReactiveBranchService extends AbstractReactiveService<Branch> implements ReactiveService<Branch> {
    public ReactiveBranchService(Dao<Branch> dao) {
        super(dao);
    }
}
