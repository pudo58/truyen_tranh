package vn.tholv.web.core.base.service;

import org.springframework.data.domain.Page;
import vn.tholv.web.core.base.dto.StoryRequest;
import vn.tholv.web.core.base.dto.StoryResponse;
import vn.tholv.web.core.base.entity.Story;
import vn.tholv.web.core.base.service.core.Service;

public interface StoryService extends Service<Story, Integer> {
    Page<StoryResponse> findBySearch(StoryRequest request);
}
