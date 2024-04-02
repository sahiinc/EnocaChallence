package enoca.market.enoca.controllerv1;


import enoca.market.enoca.dto.ProductRequest;
import enoca.market.enoca.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity createProduct(@RequestBody ProductRequest productRequest){
        System.out.println("createProduct start");
        System.out.println("productRequest: " + productRequest);
        return productService.createProduct(productRequest);
    }


    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity updateProduct(@PathVariable Long productId, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(productId, productRequest);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }


    @GetMapping("/getAllProducts")
    public ResponseEntity getAllProducts() {

        return  productService.getAllProducts();
    }


}
