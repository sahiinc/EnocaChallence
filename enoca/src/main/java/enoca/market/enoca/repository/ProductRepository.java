package enoca.market.enoca.repository;

import enoca.market.enoca.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
