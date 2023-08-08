package vn.tholv.web.core.base.service;

import org.springframework.stereotype.Service;
import vn.tholv.web.core.base.entity.Story;
import vn.tholv.web.core.base.service.core.AbstractService;

@Service
public class StoryServiceImpl extends AbstractService<Story,Integer> implements StoryService{
	@Override
	protected void validateInsert(Story entity) {

	}

	@Override
	protected void validateUpdate(Story entity) {

	}
}
