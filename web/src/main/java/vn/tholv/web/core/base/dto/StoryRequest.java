package vn.tholv.web.core.base.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StoryRequest extends PageRequest{
    private String title;
    private String sortBy;
    private Integer categoryId;
}
