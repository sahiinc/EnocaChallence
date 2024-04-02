package enoca.market.enoca.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartRequest {
    public Long quantity;
    public Long id;
}
