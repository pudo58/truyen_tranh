package vn.tholv.web.core.base.dao;

import org.springframework.stereotype.Repository;
import vn.tholv.web.core.base.dao.core.Dao;
import vn.tholv.web.core.base.entity.Category;

@Repository
public interface CategoryDao extends Dao<Category, Integer> {
}
