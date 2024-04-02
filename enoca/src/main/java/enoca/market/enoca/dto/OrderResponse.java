package enoca.market.enoca.dto;

import enoca.market.enoca.dto.base.BaseResponse;

import java.math.BigDecimal;

public class OrderResponse extends BaseResponse {
    public Long id;
    public Long customerId;
    public ProductResponse product;
    public BigDecimal amount;
    public Long quantity;
    public BigDecimal totalPrice;
}
