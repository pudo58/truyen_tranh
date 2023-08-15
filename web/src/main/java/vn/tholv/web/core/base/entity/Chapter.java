package vn.tholv.web.core.base.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import vn.tholv.web.core.base.entity.core.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chapters")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Chapter extends BaseEntity<Chapter, Integer> {
	private String title;
	private String content;
	private Integer viewCount = 0;
	private Integer likeCount = 0;
	private Integer commentCount = 0;
	private Integer priority;
	private Integer shareCount = 0;
	@ManyToOne(targetEntity = Story.class)
	@JoinColumn(name = "story_detail_id")
	private StoryDetail storyDetail;

	@OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = ChapterImage.class)
	private List<ChapterImage> chapterImageList = new ArrayList<>();
}
