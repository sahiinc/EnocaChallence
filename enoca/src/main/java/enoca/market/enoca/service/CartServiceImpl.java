package enoca.market.enoca.service;

import enoca.market.enoca.dto.CartRequest;
import enoca.market.enoca.dto.CartResponse;
import enoca.market.enoca.dto.ProductResponse;
import enoca.market.enoca.entity.Cart;
import enoca.market.enoca.entity.Product;
import enoca.market.enoca.repository.CartRepositorty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepositorty cartRepositorty;


    @Override
    public ResponseEntity getCarts() {
        List<Cart> cartList = cartRepositorty.findAll();

        List<CartResponse> cartResponses = cartList.stream()
                .map(cart -> {
                    CartResponse response = new CartResponse();

                    response.id = cart.id;
                    ProductResponse productResponse = new ProductResponse();
                    productResponse.name = cart.product.name;
                    productResponse.price = cart.product.price;

                    return response;
                })
                .collect(Collectors.toList());


        return ResponseEntity.ok(cartList);


    }

    @Override
    public ResponseEntity updateCard(CartRequest cartRequest) {
        Optional<Cart> cardOOptional = cartRepositorty.findById(cartRequest.id);
        Cart card = cardOOptional.get();
        card.quantity = cartRequest.quantity;
        cartRepositorty.save(card);
        CartResponse response = new CartResponse();

        response.id = card.id;
        ProductResponse productResponse = new ProductResponse();
        productResponse.name = card.product.name;
        productResponse.price = card.product.price;
        response.product = productResponse;

        BigDecimal sum = BigDecimal.ZERO;
        sum = sum.add(productResponse.price);
        response.totalPrice = sum;

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity emptyCard(long customerId) {
        cartRepositorty.delete(customerId);
        CartResponse response = new CartResponse();

        response.message = "sepetteki tüm ürünler silindi";

        return ResponseEntity.ok(response);
    }
}
