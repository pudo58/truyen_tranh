package vn.tholv.web.core.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.tholv.web.core.base.constant.RoleConst;
import vn.tholv.web.core.base.dao.RoleDao;
import vn.tholv.web.core.base.entity.Role;
import vn.tholv.web.core.base.entity.User;
import vn.tholv.web.core.base.service.core.AbstractService;
import vn.tholv.web.core.override.util.VerifyCaptcha;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static vn.tholv.web.core.override.util.ValidatorUtil.isNull;

@Service
public class UserServiceImpl extends AbstractService<User, Integer> implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final RoleDao roleDao;
    private final VerifyCaptcha verifyCaptcha;


    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, RoleDao roleDao, VerifyCaptcha verifyCaptcha) {
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
        this.verifyCaptcha = verifyCaptcha;
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
        try {
            if (!this.verifyCaptcha.verify(entity.getRecaptcha())) {
                throw new RuntimeException("Captcha không hợp lệ");
            }
            Optional<Role> role = roleDao.findByName(RoleConst.ROLE_USER);
            if (role.isEmpty()) {
                Role role1 = new Role();
                role1.setName(RoleConst.ROLE_USER);
                roleDao.save(role1);
            } else {
                entity.setRoleList(Set.of(role.get()));
            }
        } catch (Exception e) {
            throw new RuntimeException("Captcha không hợp lệ");
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

    @Override
    public List<User> findAll() {
        return super.findAll().stream().peek(user -> user.setPassword(null)).toList();
    }
}
