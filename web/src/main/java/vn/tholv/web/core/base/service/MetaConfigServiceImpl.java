package vn.tholv.web.core.base.service;

import org.springframework.stereotype.Service;
import vn.tholv.web.core.base.entity.MetaConfig;
import vn.tholv.web.core.base.service.core.AbstractService;

@Service
public class MetaConfigServiceImpl extends AbstractService<MetaConfig, Integer> implements MetaConfigService {
	@Override
	protected void validateInsert(MetaConfig entity) {
	}

	@Override
	protected void validateUpdate(MetaConfig entity) {
	}
}
