package az.elvinali.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatus {

    private String message;
    private int code;

    public static ResponseStatus getSuccessMessage(){
        return ResponseStatus
                .builder()
                .code(1)
                .message("process success compiled")
                .build();
    }

}
