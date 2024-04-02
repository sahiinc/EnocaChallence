package enoca.market.enoca.entity;


import enoca.market.enoca.entity.base.Base;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cart")
public class Cart extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product product;

    public Long quantity;

}
