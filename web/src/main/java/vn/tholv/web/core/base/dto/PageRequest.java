package vn.tholv.web.core.base.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@NoArgsConstructor
public class PageRequest {
	private Integer page;
	private Integer size;

	public Integer getPage() {
		if(page == null) {
			return 0;
		}
		return page;
	}

	public Integer getSize() {
		if(size == null) {
			return 10;
		}
		return size;
	}
}
