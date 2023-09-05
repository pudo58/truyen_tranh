package vn.tholv.web.controller.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.tholv.web.core.base.dto.StoryRequest;
import vn.tholv.web.core.base.dto.StoryResponse;
import vn.tholv.web.core.base.entity.Story;
import vn.tholv.web.core.base.service.StoryService;

@RestController
@RequestMapping("/v2/story")
public class StoryV2Controller {
    private StoryService storyService;

    @Autowired
    public StoryV2Controller(StoryService storyService) {
        this.storyService = storyService;
    }

    @PostMapping("/findBySearch")
    public Page<StoryResponse> findBySearch(@RequestBody StoryRequest request) {
        return storyService.findBySearch(request);
    }
}
