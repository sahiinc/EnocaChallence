package enoca.market.enoca.service;

import enoca.market.enoca.dto.ProductRequest;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity createProduct(ProductRequest productRequest);

    ResponseEntity updateProduct(Long productId, ProductRequest productRequest);

    ResponseEntity deleteProduct(Long productId);

    ResponseEntity getAllProducts();



}
