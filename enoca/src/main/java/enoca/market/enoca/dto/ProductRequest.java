package enoca.market.enoca.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    public String name;
    public BigDecimal price;
    public Long stoch;
}
