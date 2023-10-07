package az.elvinali.demo.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BaseResponse<T> {

    private T data;
    private ResponseStatus status;

}
