package vn.tholv.web.core.base.service;

import org.springframework.data.domain.Page;
import vn.tholv.web.core.base.dto.ChapterImageRequest;
import vn.tholv.web.core.base.entity.ChapterImage;
import vn.tholv.web.core.base.service.core.Service;

public interface ChapterImageService extends Service<ChapterImage, Integer> {
	Page<ChapterImage> findAllStory(ChapterImageRequest model);
}
