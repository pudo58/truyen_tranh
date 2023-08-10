package vn.tholv.web.core.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
		if (entity.getUsername().length() < 6 || entity.getUsername().length() > 20) {
			throw new RuntimeException("Tên đăng nhập phải có độ dài từ 6 đến 20 ký tự");
		}
		if (entity.getUsername().matches(".*\\s.*")) {
			throw new RuntimeException("Tên đăng nhập không được chứa khoảng trắng");
		}
		if (isNull(entity.getPassword())) {
			throw new RuntimeException("Mật khẩu không được để trống");
		} else {
			if (entity.getPassword().length() < 6) {
				throw new RuntimeException("Mật khẩu phải có ít nhất 6 ký tự");
			}
			entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		}
	}

	@Override
	protected void validateUpdate(User entity) {

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.repository.findAll((root, query, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("username"), username)
		).stream().findFirst().orElse(null);
	}
}
