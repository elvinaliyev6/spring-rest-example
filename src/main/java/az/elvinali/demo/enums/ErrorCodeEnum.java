package az.elvinali.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    VALIDATION(410,"Validation error"),
    EMPLOYEE_NOT_FOUND(409,"Employee not found"),
    UNKNOWN(408,"Unknown error");


    private int code;
    private String message;




}
