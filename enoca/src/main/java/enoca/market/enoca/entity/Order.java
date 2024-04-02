package enoca.market.enoca.entity;

import enoca.market.enoca.entity.base.Base;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order")
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
