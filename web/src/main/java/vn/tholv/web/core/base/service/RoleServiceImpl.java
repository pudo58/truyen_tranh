package vn.tholv.web.core.base.service;

import org.springframework.stereotype.Service;
import vn.tholv.web.core.base.entity.Role;
import vn.tholv.web.core.base.service.core.AbstractService;

@Service
public class RoleServiceImpl extends AbstractService<Role,Integer> implements RoleService {
	@Override
	protected void validateInsert(Role entity) {

	}

	@Override
	protected void validateUpdate(Role entity) {

	}
}
