package vn.tholv.web.core.base.dao;

import org.springframework.stereotype.Repository;
import vn.tholv.web.core.base.dao.core.Dao;
import vn.tholv.web.core.base.entity.Chapter;

@Repository
public interface ChapterDao extends Dao<Chapter, Integer> {
}
