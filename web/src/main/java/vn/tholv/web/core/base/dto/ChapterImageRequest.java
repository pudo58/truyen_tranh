package vn.tholv.web.core.base.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChapterImageRequest extends PageRequest{
	private Integer storyId;
	private Integer chapterId;
}
