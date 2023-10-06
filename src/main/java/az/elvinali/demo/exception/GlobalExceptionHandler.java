package az.elvinali.demo.exception;

import az.elvinali.demo.dto.response.BaseResponse;
import az.elvinali.demo.dto.response.ResponseStatus;
import az.elvinali.demo.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleBaseException(BaseException ex) {
        ResponseStatus status = new ResponseStatus();
        status.setCode(ex.getCode());
        status.setMessage(ex.getMessage());

        BaseResponse<Void> response = BaseResponse.<Void>builder()
                .status(status)
                .build();
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        ResponseStatus status = new ResponseStatus();
        status.setCode(ErrorCodeEnum.VALIDATION.getCode());
        status.setMessage(ErrorCodeEnum.VALIDATION.getMessage());

        BaseResponse<Void> response = BaseResponse.<Void>builder()
                .status(status)
                .build();
        return response;
    }

    @ExceptionHandler(Exception.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<Void> handleUnknownException(Exception ex) {
        ex.printStackTrace();

        ResponseStatus status = new ResponseStatus();
        status.setCode(ErrorCodeEnum.UNKNOWN.getCode());
        status.setMessage(ErrorCodeEnum.UNKNOWN.getMessage());

        BaseResponse<Void> response = BaseResponse.<Void>builder()
                .status(status)
                .build();
        return response;
    }
}
