package enoca.market.enoca.dto.base;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class BaseResponse {
    public String status;
    public int code;
    public String message;
}
