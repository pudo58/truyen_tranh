package vn.tholv.web.core.base.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import vn.tholv.web.core.base.entity.core.BaseEntity;

@Entity
@Table(name = "meta_config")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class MetaConfig extends BaseEntity<MetaConfig, Integer> {
	@Lob
	private String title;
	@Lob
	private String description;
	private String keywords;
	private String logoUrl;
	private String faviconUrl;
	private Integer objectId;
	private String objectType;
}
