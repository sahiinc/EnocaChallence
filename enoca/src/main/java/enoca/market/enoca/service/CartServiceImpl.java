package enoca.market.enoca.service;

import enoca.market.enoca.dto.CartRequest;
import enoca.market.enoca.dto.CartResponse;
import enoca.market.enoca.dto.ProductResponse;
import enoca.market.enoca.entity.Cart;
import enoca.market.enoca.entity.Customer;
import enoca.market.enoca.entity.Product;
import enoca.market.enoca.repository.CartRepositorty;
import enoca.market.enoca.repository.CustomerRepository;
import enoca.market.enoca.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepositorty cartRepositorty;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity getCarts() {
        List<Cart> cartList = cartRepositorty.findAll();

        List<CartResponse> cartResponses = cartList.stream()
                .map(cart -> {
                    CartResponse response = new CartResponse();

                    response.setId(cart.getId());
                    ProductResponse productResponse = new ProductResponse();
                    productResponse.setName(cart.getProduct().getName());
                    productResponse.setPrice(cart.getProduct().getPrice());

                    return response;
                })
                .collect(Collectors.toList());


        return ResponseEntity.ok(cartList);


    }

    @Override
    public ResponseEntity updateCard(CartRequest cartRequest) {
        Optional<Cart> cardOOptional = cartRepositorty.findById(cartRequest.id);
        Cart card = cardOOptional.get();
        card.setQuantity(cartRequest.getQuantity());
        cartRepositorty.save(card);
        CartResponse response = new CartResponse();

        response.setId(card.getId());
        ProductResponse productResponse = new ProductResponse();
        productResponse.setName(card.getProduct().getName());
        productResponse.setPrice(card.getProduct().getPrice());
        response.setProduct(productResponse);

        BigDecimal sum = BigDecimal.ZERO;
        sum = sum.add(productResponse.price);
        response.setTotalPrice(sum);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity emptyCard(Long customerId) {
        log.info("empty cart start emptycart for customerId :{} ", customerId);
        cartRepositorty.delete(customerId);

        CartResponse response = new CartResponse();
        response.setMessage("sepetteki tüm ürünler silindi");
        log.info("empty cart end emptycart for customerId :{} ", customerId);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity addProductToCart(Long productId, Long customerId, Long quantity) {
        log.info("addProductToCart start cart for productId : {} , customerId: {} , quantity: {}", productId, customerId, quantity);
        Optional<Customer> customer = customerRepository.findById(customerId);
        Optional<Product> product = productRepository.findById(productId);
        Cart cart = new Cart();
        cart.setProduct(product.get());
        cart.setCustomer(customer.get());
        cart.setQuantity(quantity);
        cartRepositorty.save(cart);
        CartResponse response = new CartResponse();
        response.setMessage("Ürün sepete eklendi.");
        log.info("addProductToCart end cart for productId : {} , customerId: {} , quantity: {}", productId, customerId, quantity);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity removeProductFromCart(Long id) {
        log.info("deleting start cart for id : {}", id);
        cartRepositorty.deleteById(id);
        CartResponse response = new CartResponse();
        response.setMessage("Ürün sepetten çıkartıldı.");
        log.info("deleting end cart for id : {}", id);
        return ResponseEntity.ok(response);
    }


}
