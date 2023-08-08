package vn.tholv.web.core.base.service;

import org.springframework.stereotype.Service;
import vn.tholv.web.core.base.entity.Chapter;
import vn.tholv.web.core.base.service.core.AbstractService;

@Service
public class ChapterServiceImpl extends AbstractService<Chapter, Integer> implements ChapterService {

	@Override
	protected void validateInsert(Chapter entity) {

	}

	@Override
	protected void validateUpdate(Chapter entity) {

	}
}
