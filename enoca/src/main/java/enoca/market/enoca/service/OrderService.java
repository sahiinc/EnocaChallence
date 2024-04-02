package enoca.market.enoca.service;

import enoca.market.enoca.dto.OrderRequest;
import enoca.market.enoca.dto.OrderResponse;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity getAllOrdersForCustomer(Long customerId);
    ResponseEntity getOrdersForCode(Long customerId, Long orderId);
}
