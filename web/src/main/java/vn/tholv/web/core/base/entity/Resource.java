package vn.tholv.web.core.base.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.tholv.web.core.base.entity.core.BaseEntity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Resource extends BaseEntity<Resource, Integer> {
	@ManyToOne
	@JoinColumn(name = "story_id")
	private Story story;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	private  Boolean isActive = Boolean.TRUE;

}
