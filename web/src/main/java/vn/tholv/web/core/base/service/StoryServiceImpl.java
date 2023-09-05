package vn.tholv.web.core.base.service;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.tholv.web.core.base.constant.StoryConst;
import vn.tholv.web.core.base.dto.StoryRequest;
import vn.tholv.web.core.base.dto.StoryResponse;
import vn.tholv.web.core.base.entity.Story;
import vn.tholv.web.core.base.entity.StoryDetail;
import vn.tholv.web.core.base.entity.core.BaseEntity;
import vn.tholv.web.core.base.service.core.AbstractService;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoryServiceImpl extends AbstractService<Story, Integer> implements StoryService {
    @Override
    protected void validateInsert(Story entity) {
        nothingValidate();
    }

    @Override
    protected void validateUpdate(Story entity) {
        nothingValidate();
    }

    @Override
    public Page<StoryResponse> findBySearch(StoryRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<StoryResponse> storyPage = this.repository.findAll((root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();
            Root<StoryDetail> storyDetailRoot = query.from(StoryDetail.class);
            predicates.add(builder.equal(root.get(BaseEntity._id), storyDetailRoot.get("story").get(BaseEntity._id)));
            if (request.getTitle() != null && !request.getTitle().isEmpty()) {
                predicates.add(builder.like(root.get("title"), "%" + request.getTitle() + "%"));
            }
            if (request.getSortBy() == null) {
                request.setSortBy(StoryConst.SORT_BY_CREATED_DATE);
            }
            if (request.getCategoryId() != null) {
                predicates.add(builder.equal(root.get("category").get(BaseEntity._id), request.getCategoryId()));
            }
            if (StoryConst.SORT_BY_TITLE.equals(request.getSortBy())) {
                query.orderBy(builder.asc(root.get("title")), builder.desc(root.get(BaseEntity._id)));
            } else if (StoryConst.SORT_BY_CREATED_DATE.equals(request.getSortBy())) {
                query.orderBy(builder.desc(root.get(BaseEntity._createdDate)), builder.desc(root.get(BaseEntity._id)));
            } else if (StoryConst.SORT_BY_UPDATED_DATE.equals(request.getSortBy())) {
                query.orderBy(builder.desc(root.get(BaseEntity._modifiedDate)), builder.desc(root.get(BaseEntity._id)));
            } else if (StoryConst.SORT_BY_VIEW_COUNT.equals(request.getSortBy())) {
                query.orderBy(builder.desc(root.get("viewCount")), builder.desc(root.get(BaseEntity._id)));
            } else if (StoryConst.SORT_BY_LIKE_COUNT.equals(request.getSortBy())) {
                query.orderBy(builder.desc(root.get("likeCount")), builder.desc(root.get(BaseEntity._id)));
            }
            query.distinct(true);
            query.multiselect(
                root, storyDetailRoot, builder.countDistinct(storyDetailRoot.get(BaseEntity._id))
            ).groupBy(root.get(BaseEntity._id));
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable, StoryResponse.class);
        return storyPage;
    }

    private int getChapterCount(Story story) {
        return story.getStoryDetailList().size();
    }

}
