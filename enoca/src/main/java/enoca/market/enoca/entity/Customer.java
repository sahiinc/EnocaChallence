package enoca.market.enoca.entity;

import enoca.market.enoca.entity.base.Base;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customer")
public class Customer extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String commercialName;
    public String firstName;
    public String lastName;


}
