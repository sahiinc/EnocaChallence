package enoca.market.enoca.controllerv1;

import enoca.market.enoca.dto.CartRequest;
import enoca.market.enoca.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/GetCart")
    public ResponseEntity getCard() {
        return cartService.getCarts();
    }

    @PutMapping("/UpdateCart")
    public ResponseEntity updateCard(@RequestBody CartRequest cartRequest) {
        return cartService.updateCard(cartRequest);
    }

    @DeleteMapping("/EmptyCart/{customerId}")
    public ResponseEntity emptyCart(@PathVariable Long customerId) {
        return cartService.emptyCard(customerId);
    }

    @PostMapping("/AddProductToCart")
    public ResponseEntity addProductToCart(@PathVariable Long productId, @PathVariable  Long customerId, @PathVariable  Long quantity){

        return cartService.addProductToCart(productId,customerId, quantity);
    }

    @DeleteMapping("/RemoveProductFromCart")
    public ResponseEntity removeProductFromCart(@PathVariable Long id) {
        return cartService.removeProductFromCart(id);
    }


}
