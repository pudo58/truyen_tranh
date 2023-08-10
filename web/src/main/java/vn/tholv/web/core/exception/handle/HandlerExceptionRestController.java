package vn.tholv.web.core.exception.handle;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.tholv.web.core.exception.ExceptionError;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class HandlerExceptionRestController {
    @ExceptionHandler(RuntimeException.class)
    public ExceptionError handleException(Exception ex) {
        ExceptionError error = new ExceptionError();
        error.setMessage("Có lỗi xảy ra trong quá trình xử lý");
        error.setDetail(ex.getMessage());
	    error.setIsError(true);
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return error;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ExceptionError handleResourceNotFoundException(AccessDeniedException ex) {
        ExceptionError error = new ExceptionError();
        error.setMessage("Bạn không có quyền truy cập");
        error.setDetail(ex.getMessage());
		error.setIsError(true);
        error.setStatus(HttpStatus.FORBIDDEN.value());
        return error;
    }

    @ExceptionHandler(Exception.class)
    public ExceptionError handleResourceNotFoundException(Exception ex) {
        ExceptionError error = new ExceptionError();
        error.setMessage("Lỗi hệ thống");
	    error.setIsError(true);
        error.setDetail(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return error;
    }

	@ExceptionHandler(UsernameNotFoundException.class)
	public ExceptionError handleResourceNotFoundException(NullPointerException ex) {
		ExceptionError error = new ExceptionError();
		error.setMessage("Không tìm thấy người dùng");
	    error.setIsError(true);
		error.setDetail(ex.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		return error;
	}
}
