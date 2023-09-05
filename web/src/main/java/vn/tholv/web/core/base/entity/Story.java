package vn.tholv.web.core.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import vn.tholv.web.core.base.entity.core.BaseEntity;

import java.util.List;

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
    private int likeCount = 0;
    private int viewCount = 0;


    @JoinColumn(name = "category_id")
    @ManyToOne(targetEntity = Category.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = StoryDetail.class, fetch = FetchType.LAZY)
    private List<StoryDetail> storyDetailList;

}
