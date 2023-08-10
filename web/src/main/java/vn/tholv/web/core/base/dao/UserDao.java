package vn.tholv.web.core.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.tholv.web.core.base.dao.core.Dao;
import vn.tholv.web.core.base.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>, Dao<User, Integer> {
	User findByUsername(String username);
}
