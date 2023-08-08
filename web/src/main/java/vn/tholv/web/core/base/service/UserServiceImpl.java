package vn.tholv.web.core.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.tholv.web.core.base.entity.User;
import vn.tholv.web.core.base.service.core.AbstractService;

import static vn.tholv.web.core.override.util.ValidatorUtil.isNull;

@Service
public class UserServiceImpl extends AbstractService<User, Integer> implements UserService {
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User save(User entity) {
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		return super.save(entity);
	}

	@Override
	protected void validateInsert(User entity) {
		if (isNull(entity)) {
			throw new RuntimeException("Dữ liệu không hợp lệ");
		}
		if (isNull(entity.getUsername())) {
			throw new RuntimeException("Tên đăng nhập không được để trống");
		}
		if (isNull(entity.getPassword())) {
			throw new RuntimeException("Mật khẩu không được để trống");
		}
	}

	@Override
	protected void validateUpdate(User entity) {

	}
}
