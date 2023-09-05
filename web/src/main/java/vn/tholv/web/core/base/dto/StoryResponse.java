package vn.tholv.web.core.base.dto;

import lombok.Getter;
import lombok.Setter;
import vn.tholv.web.core.base.entity.Story;

@Setter
@Getter
public class StoryResponse extends Story {
    private int chapterCount;
    public StoryResponse() {
    }
    public StoryResponse(Story story ,int chapterCount) {
        copyData(story);
        this.chapterCount = chapterCount;
    }
    @Override
    public void copyData(Story object) {
        super.copyData(object);
    }
}
