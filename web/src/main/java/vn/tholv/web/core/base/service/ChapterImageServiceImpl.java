package vn.tholv.web.core.base.service;

import jakarta.persistence.criteria.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.tholv.web.core.base.dao.ResourceDao;
import vn.tholv.web.core.base.dto.ChapterImageRequest;
import vn.tholv.web.core.base.entity.ChapterImage;
import vn.tholv.web.core.base.entity.Resource;
import vn.tholv.web.core.base.entity.Story;
import vn.tholv.web.core.base.entity.User;
import vn.tholv.web.core.base.entity.core.BaseEntity;
import vn.tholv.web.core.base.service.core.AbstractService;

import java.util.List;

@Service
public class ChapterImageServiceImpl extends AbstractService<ChapterImage, Integer> implements ChapterImageService {
	private ResourceDao resourceDao;

	@Autowired
	public ChapterImageServiceImpl(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	@Override
	protected void validateInsert(ChapterImage entity) {

	}

	@Override
	protected void validateUpdate(ChapterImage entity) {

	}

	@Override
	public List<ChapterImage> findAll() {
		return null;
	}


	@Override
	public Page<ChapterImage> findAllStory(ChapterImageRequest model) {
		User user = User.getCurrentUser();
		Resource resource = this.getResource(model.getStoryId());
		if (resource != null) {
			if (user.getLevel() < resource.getLevel()) {
				throw new RuntimeException("Bạn không có quyền xem chương này");
			}
		}
		Pageable pageable = PageRequest.of(model.getPage(), model.getSize());
		return this.repository.findAll((root, query, builder) ->
				builder.equal(root.get("chapter").get(BaseEntity._id), model.getChapterId())
			, pageable);
	}

	private Resource getResource(Integer storyId) {
		return resourceDao.findAll((root, query, builder) -> {
			Join<Resource, Story> storyJoin = root.join("story");
			return builder.equal(storyJoin.get(BaseEntity._id), storyId);
		}).stream().findFirst().orElse(null);
	}
}
