package enoca.market.enoca.repository;

import enoca.market.enoca.dto.CartRequest;
import enoca.market.enoca.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepositorty extends JpaRepository<Cart,Long> {

    List<Cart> findCartsByCriteria(CartRequest cartRequest);

    @Query(nativeQuery = true, value = "delete from cart where customer_id=:customerId")
    void delete(Long customerId);
}
