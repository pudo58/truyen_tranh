package vn.tholv.web.core.base.service;

import org.springframework.stereotype.Service;
import vn.tholv.web.core.base.entity.Category;
import vn.tholv.web.core.base.service.core.AbstractService;

@Service
public class CategoryServiceImpl extends AbstractService<Category, Integer> implements CategoryService {
	@Override
	protected void validateInsert(Category entity) {

	}

	@Override
	protected void validateUpdate(Category entity) {

	}
}
