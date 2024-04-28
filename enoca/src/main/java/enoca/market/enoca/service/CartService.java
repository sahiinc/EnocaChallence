package enoca.market.enoca.service;

import enoca.market.enoca.dto.CartRequest;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity getCarts();

    ResponseEntity updateCard( CartRequest cartRequest);

    ResponseEntity emptyCard(Long customerId);

    ResponseEntity addProductToCart(Long productId, Long customerId, Long quantity);

    ResponseEntity removeProductFromCart(Long id);
}
