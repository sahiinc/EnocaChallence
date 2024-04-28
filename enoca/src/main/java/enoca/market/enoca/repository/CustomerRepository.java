package enoca.market.enoca.repository;

import enoca.market.enoca.entity.Customer;
import enoca.market.enoca.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {


}
