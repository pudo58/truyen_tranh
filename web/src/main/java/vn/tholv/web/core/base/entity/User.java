package vn.tholv.web.core.base.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import vn.tholv.web.core.base.constant.UserConst;
import vn.tholv.web.core.base.entity.core.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class User extends BaseEntity<User, Integer> {
    private String username;
    private String password;
    private String email;
    private int level = UserConst.LEVEL_BRONZE;
    private int status = UserConst.STATUS_ACTIVE;

    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Role> roleList = new HashSet<>();
}
