package vn.tholv.web.core.base.service;

import org.springframework.stereotype.Service;
import vn.tholv.web.core.base.entity.StoryDetail;
import vn.tholv.web.core.base.service.core.AbstractService;

@Service
public class StoryDetailServiceImpl extends AbstractService<StoryDetail, Integer> implements StoryDetailService {
	@Override
	protected void validateInsert(StoryDetail entity) {

	}

	@Override
	protected void validateUpdate(StoryDetail entity) {

	}
}
