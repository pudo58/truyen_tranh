package vn.tholv.web.core.base.service;

import org.springframework.stereotype.Service;
import vn.tholv.web.core.base.entity.User;
import vn.tholv.web.core.base.service.core.AbstractService;

@Service
public class UserServiceImpl extends AbstractService<User, Integer> implements UserService {
	@Override
	protected void validateInsert(User entity) {
	}

	@Override
	protected void validateUpdate(User entity) {

	}
}
