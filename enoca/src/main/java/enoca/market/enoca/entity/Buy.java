package enoca.market.enoca.entity;

import enoca.market.enoca.entity.base.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Buy extends Base {
    @Id
    public Long id;
    public String productName;
    public BigDecimal productPrice;

}
