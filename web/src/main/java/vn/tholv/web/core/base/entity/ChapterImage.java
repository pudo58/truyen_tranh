package vn.tholv.web.core.base.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import vn.tholv.web.core.base.entity.core.BaseEntity;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class ChapterImage extends BaseEntity<ChapterImage, Integer> {
	@Lob
	private String imageData;
	private Integer priority;

	@ManyToOne(targetEntity = Chapter.class)
	@JoinColumn(name = "chapter_id")
	private Chapter chapter;

}
