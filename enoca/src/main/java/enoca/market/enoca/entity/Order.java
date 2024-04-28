package enoca.market.enoca.entity;

import enoca.market.enoca.entity.base.Base;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order")
@Getter
@Setter
public class Order extends Base {
    @Id
    public Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer customer;
    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product product;
    public BigDecimal amount;
    public Long quantity;

}
