package vn.tholv.web.core.base.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import vn.tholv.web.core.base.entity.User;
import vn.tholv.web.core.base.service.core.Service;

public interface UserService extends Service<User,Integer> , UserDetailsService {
}
