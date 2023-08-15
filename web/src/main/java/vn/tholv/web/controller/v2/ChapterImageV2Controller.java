package vn.tholv.web.controller.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.tholv.web.core.base.dto.ChapterImageRequest;
import vn.tholv.web.core.base.entity.ChapterImage;
import vn.tholv.web.core.base.service.ChapterImageService;

@RestController
@RequestMapping("/v2/chapter-image")
public class ChapterImageV2Controller {
	private ChapterImageService chapterImageService;

	@Autowired
	public ChapterImageV2Controller(ChapterImageService chapterImageService) {
		this.chapterImageService = chapterImageService;
	}

	@PostMapping("/findAllStory")
	public Page<ChapterImage> findAllStory(@RequestBody ChapterImageRequest model) {
		return chapterImageService.findAllStory(model);
	}
}
