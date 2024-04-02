package enoca.market.enoca.service;

import enoca.market.enoca.dto.OrderResponse;
import enoca.market.enoca.dto.ProductResponse;
import enoca.market.enoca.entity.Order;
import enoca.market.enoca.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public ResponseEntity getAllOrdersForCustomer(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        List<OrderResponse> responses = new ArrayList<>();
        orders.forEach(order -> {
            OrderResponse response = new OrderResponse();
            response.totalPrice=order.amount;
            response.quantity=order.quantity;
            ProductResponse productResponse = new ProductResponse();
            productResponse.price = order.product.price;
            productResponse.name = order.product.name;
            response.product = productResponse;
            responses.add(response);
        });
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity getOrdersForCode(Long customerId, Long orderId) {
        Optional<Order> orderOptional = orderRepository.findByCustomerIdAndOrderCode(customerId, orderId);

        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            OrderResponse response = new OrderResponse();
            response.totalPrice = order.amount;
            response.quantity = order.quantity;
            ProductResponse productResponse = new ProductResponse();
            productResponse.price = order.product.price;
            productResponse.name = order.product.name;
            response.product = productResponse;

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
