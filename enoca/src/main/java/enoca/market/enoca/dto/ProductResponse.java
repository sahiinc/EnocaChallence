package enoca.market.enoca.dto;

import enoca.market.enoca.dto.base.BaseResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class ProductResponse extends BaseResponse {
    public Long id;
    public String name;
    public BigDecimal price;
    public Long stoch;

}
