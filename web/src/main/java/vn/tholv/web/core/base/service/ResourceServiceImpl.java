package vn.tholv.web.core.base.service;

import org.springframework.stereotype.Service;
import vn.tholv.web.core.base.entity.Resource;
import vn.tholv.web.core.base.service.core.AbstractService;

@Service
public class ResourceServiceImpl extends AbstractService<Resource,Integer> implements ResourceService{
	@Override
	protected void validateInsert(Resource entity) {

	}

	@Override
	protected void validateUpdate(Resource entity) {

	}
}
