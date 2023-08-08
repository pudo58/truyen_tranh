package vn.tholv.web.core.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ExceptionError implements Serializable {
    private String message;
    private String detail;
    private int status;
	private Boolean isError;
}
