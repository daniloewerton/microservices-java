package br.com.daniloewerton.business;

import br.com.daniloewerton.apiv1.dto.ProductResponse;
import br.com.daniloewerton.apiv1.entity.Product;
import br.com.daniloewerton.infrastructure.client.ProductClient;
import br.com.daniloewerton.infrastructure.converter.ProductConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FakeApiService {

    private final ProductClient productClient;
    private final ProductService productService;
    private final ProductConverter productConverter;

    public List<ProductResponse> getAndSaveProducts() {
        List<ProductResponse> response = productClient.getProducts();
        response.stream()
                .map(productConverter::toRequest)
                        .forEach(item -> {
                            boolean productExists = productService.existsByTitle(item.title());
                            Product product = productConverter.toEntity(item);
                            if (!productExists) {
                                productService.saveProduct(product);
                            }
                        });
        return productService.getAllProducts();
    }
}