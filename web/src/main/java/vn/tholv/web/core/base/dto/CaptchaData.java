package vn.tholv.web.core.base.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaptchaData {
    private Boolean success;
    private String challenge_ts;
    private String hostname;
    private String errorCodes;
}
