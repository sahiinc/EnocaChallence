package enoca.market.enoca.service;

import enoca.market.enoca.dto.CartRequest;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity getCarts();

    ResponseEntity updateCard( CartRequest cartRequest);

    ResponseEntity emptyCard(long customerId);
}
