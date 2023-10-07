package az.elvinali.demo.exception;


import az.elvinali.demo.enums.ErrorCodeEnum;
import lombok.Data;

@Data

public class BaseException extends RuntimeException{

    private int code;
    private String message;

    public BaseException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getMessage());
        this.code=errorCodeEnum.getCode();
        this.message=errorCodeEnum.getMessage();
    }

}
