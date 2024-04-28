package enoca.market.enoca.service;

import enoca.market.enoca.dto.OrderResponse;
import enoca.market.enoca.dto.ProductResponse;
import enoca.market.enoca.entity.Cart;
import enoca.market.enoca.entity.Customer;
import enoca.market.enoca.entity.Order;
import enoca.market.enoca.entity.Product;
import enoca.market.enoca.repository.CustomerRepository;
import enoca.market.enoca.repository.OrderRepository;
import enoca.market.enoca.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;


    @Override
    public ResponseEntity getAllOrdersForCustomer(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        List<OrderResponse> responses = new ArrayList<>();
        orders.forEach(order -> {

            OrderResponse response = new OrderResponse();
            response.setTotalPrice(order.getAmount());
            response.setQuantity(order.getQuantity());
            ProductResponse productResponse = new ProductResponse();
            productResponse.setPrice(order.getProduct().getPrice());
            productResponse.setName(order.getProduct().getName());
            response.setProduct(productResponse);
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
            response.setTotalPrice(order.getAmount());
            response.setQuantity(order.getQuantity());
            ProductResponse productResponse = new ProductResponse();
            productResponse.setPrice(order.getProduct().getPrice());
            productResponse.setName(order.getProduct().getName());
            response.setProduct(productResponse);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
