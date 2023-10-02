package az.elvinali.demo.exception;


import lombok.Data;

@Data

public class BaseException extends RuntimeException{

    public BaseException(String message){
        super(message);
    }

}
