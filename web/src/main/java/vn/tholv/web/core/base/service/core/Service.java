package vn.tholv.web.core.base.service.core;

import org.springframework.data.domain.Page;
import vn.tholv.web.core.base.entity.core.BaseEntity;

import java.util.List;

public interface Service<T extends BaseEntity<T, ID>, ID> {
	T findById(String id) throws Exception;

	T save(T entity);

	void deleteById(String id) throws Exception;

	T update(T entity) throws Exception;

	List<T> priority(List<T> entityList);

	List<T> findAll();

	List<T> findAllByCondition(T t);

	Page<T> findPageByCondition(T t, int page, int size);

	Page<T> findPage(int page, int size);
}
