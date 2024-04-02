package enoca.market.enoca.dto;

import java.math.BigDecimal;

public class OrderRequest {
    public Long customerId;
    public Long productId;
    public BigDecimal amount;
    public Long quantity;
}
