package vn.tholv.web.core.base.service;

import org.springframework.stereotype.Service;
import vn.tholv.web.core.base.entity.ChapterImage;
import vn.tholv.web.core.base.service.core.AbstractService;

@Service
public class ChapterImageServiceImpl extends AbstractService<ChapterImage, Integer> implements ChapterService {
	@Override
	protected void validateInsert(ChapterImage entity) {

	}

	@Override
	protected void validateUpdate(ChapterImage entity) {

	}
}
