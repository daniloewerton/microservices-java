package br.com.daniloewerton.business;

import br.com.daniloewerton.apiv1.dto.ProductRequest;
import br.com.daniloewerton.apiv1.dto.ProductResponse;
import br.com.daniloewerton.apiv1.entity.Product;
import br.com.daniloewerton.apiv1.exception.EntityNotFoundException;
import br.com.daniloewerton.infrastructure.converter.ProductConverter;
import br.com.daniloewerton.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public ProductResponse getProductByName(String title) {
        try {
            Product product = productRepository.findByTitle(title);
            return productConverter.toDTO(product);
        } catch (Exception exception) {
            throw new EntityNotFoundException("Produto inexistente");
        }
    }

    public void delete(String title) {
        getProductByName(title);
        productRepository.deleteByTitle(title);
    }

    public ProductResponse saveProductDTO(ProductRequest request) {
        Product entity = productConverter.toEntity(request);
        return productConverter.toDTO(productRepository.save(entity));
    }

    public ProductResponse updateProduct(String id, ProductResponse response) {
        Product entity = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto inexistente"));

        Product entityUpdated = productConverter.toEntityUpdate(id, entity, response);
        saveProduct(entityUpdated);
        return productConverter.toDTO(productRepository.findByTitle(entityUpdated.getTitle()));
    }

    public List<ProductResponse> getAllProducts() {
        return productConverter.toListDTO(productRepository.findAll());
    }

    public boolean existsByTitle(String title) {
        return productRepository.existsByTitle(title);
    }
}
