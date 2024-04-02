package enoca.market.enoca.service;


import enoca.market.enoca.dto.ProductRequest;
import enoca.market.enoca.dto.ProductResponse;
import enoca.market.enoca.entity.Product;
import enoca.market.enoca.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity createProduct(ProductRequest productRequest) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.code = 0;
        productResponse.status = "succeed";
        productResponse.message = "Ürün ekleme başarılı";

        Product product = new Product();
        product.name = productRequest.name;
        product.price = productRequest.price;
        product.stoch = productRequest.stoch;

        product = productRepository.save(product);
        productResponse.id=product.id;

        return ResponseEntity.ok(productResponse);
    }

    @Override
    public ResponseEntity updateProduct(Long productId, ProductRequest productRequest) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();



            existingProduct.name = productRequest.name;
            existingProduct.price=productRequest.price;
            existingProduct.stoch=productRequest.stoch;

            // Güncellenmiş ürünü kaydediyoruz
            Product updatedProduct = productRepository.save(existingProduct);

            // Başarı mesajı ile güncellenmiş ürünü yanıt olarak döndürdük
            ProductResponse productResponse = new ProductResponse();
            productResponse.code = 0;
            productResponse.status = "succeed";
            productResponse.message = "Ürün güncelleme başarılı";
            productResponse.id = updatedProduct.id;

            return ResponseEntity.ok(productResponse);
        } else {
            // Ürün bulunamazsa hata döndür
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ürün bulunamadı");
        }
    }

    @Override
    public ResponseEntity deleteProduct(Long productId) {

        productRepository.deleteById(productId);
        ProductResponse productResponse = new ProductResponse();
        productResponse.code = 0;
        productResponse.status = "succeed";
        productResponse.message = "Ürün silme başarılı";
        return ResponseEntity.ok(productResponse);
    }

    @Override
    public ResponseEntity getAllProducts() {
        List<Product> productList = productRepository.findAll();

        // Ürün listesini dönüş formatına cevirdik
        List<ProductResponse> productResponses = productList.stream()
                .map(product -> {
                    ProductResponse response = new ProductResponse();
                    response.id = product.id;
                    response.name=product.name;
                    response.price=product.price;
                    response.stoch=product.stoch;
                    return response;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(productResponses);
    }


}
