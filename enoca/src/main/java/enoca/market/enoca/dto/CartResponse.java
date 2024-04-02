package enoca.market.enoca.dto;

import enoca.market.enoca.dto.base.BaseResponse;
import enoca.market.enoca.entity.base.Base;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartResponse extends BaseResponse {
    public Long id;

    public ProductResponse product;

    public BigDecimal totalPrice;

}
