package co.com.seti.usecase.user.common.exception;

import lombok.Getter;

@Getter
public class ErrorException extends Exception {

    private final Integer code;

    public ErrorException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
