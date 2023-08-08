package vn.tholv.web.core.base.dao;

import org.springframework.stereotype.Repository;
import vn.tholv.web.core.base.dao.core.Dao;
import vn.tholv.web.core.base.entity.Role;

@Repository
public interface RoleDao extends Dao<Role, Integer> {
}
