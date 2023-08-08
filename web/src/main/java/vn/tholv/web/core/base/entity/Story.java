package vn.tholv.web.core.base.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import vn.tholv.web.core.base.entity.core.BaseEntity;

@Entity
@Table(name = "stories")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@DynamicUpdate
public class Story extends BaseEntity<Story, Integer> {
	private String title;
	private String imageUrl;
	private int likeCount;
	private int viewCount;

	@JoinColumn(name = "category_id")
	@ManyToOne(targetEntity = Category.class,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Category category;
}
