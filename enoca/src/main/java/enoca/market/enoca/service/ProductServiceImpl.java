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
        productResponse.setMessage("Ürün ekleme başarılı");

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setStoch(productRequest.getStoch());

        product = productRepository.save(product);
        productResponse.setId(product.getId());

        return ResponseEntity.ok(productResponse);
    }

    @Override
    public ResponseEntity updateProduct(Long productId, ProductRequest productRequest) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();



            existingProduct.setName(productRequest.getName());
            existingProduct.setPrice(productRequest.getPrice());
            existingProduct.setStoch(productRequest.getStoch());

            // Güncellenmiş ürünü kaydediyoruz
            Product updatedProduct = productRepository.save(existingProduct);

            // Başarı mesajı ile güncellenmiş ürünü yanıt olarak döndürdük
            ProductResponse productResponse = new ProductResponse();
            productResponse.setMessage("Ürün güncelleme başarılı");
            productResponse.setId(updatedProduct.getId());

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
        productResponse.setMessage("Ürün silme başarılı");
        return ResponseEntity.ok(productResponse);
    }

    @Override
    public ResponseEntity getAllProducts() {
        List<Product> productList = productRepository.findAll();

        // Ürün listesini dönüş formatına cevirdik
        List<ProductResponse> productResponses = productList.stream()
                .map(product -> {
                    ProductResponse response = new ProductResponse();
                    response.setId(product.getId());
                    response.setName(product.getName());
                    response.setPrice(product.getPrice());
                    response.setStoch(product.getStoch());
                    return response;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(productResponses);
    }


}
