package com.irm;
/**
 * @author M.hj
 * @version 1.0
 * @date 2020/9/25 16:02
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClassNotFoundException extends  RuntimeException {
    public ClassNotFoundException() {
        super();
    }

    public ClassNotFoundException(String message) {
        super(message);
    }

    public ClassNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
