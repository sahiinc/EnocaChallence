package enoca.market.enoca.repository;

import enoca.market.enoca.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByCustomerId(Long customerId);

    Optional<Order> findByCustomerIdAndOrderCode(Long customerId, Long orderId);
}
