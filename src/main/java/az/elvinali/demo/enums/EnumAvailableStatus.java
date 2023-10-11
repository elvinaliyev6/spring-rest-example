package az.elvinali.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.*;

@AllArgsConstructor
@Getter
public enum EnumAvailableStatus {

    ACTIVE(1),
    DEACTIVE(0);

    private int value;


}
