package vn.tholv.web.core.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import vn.tholv.web.core.base.entity.core.BaseEntity;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DynamicUpdate
public class StoryDetail extends BaseEntity<StoryDetail, Integer> {
	private String content;
	private String imageUrl; // ảnh đại diện
	private String title; // tiêu đề chương
	private Integer priority; // thứ tự chương

	@ManyToOne(targetEntity = Story.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "story_id")
    @JsonIgnoreProperties("storyDetailList")
	private Story story;
}
