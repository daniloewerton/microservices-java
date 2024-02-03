package br.com.daniloewerton.infrastructure.converter;

import br.com.daniloewerton.apiv1.dto.ProductRequest;
import br.com.daniloewerton.apiv1.dto.ProductResponse;
import br.com.daniloewerton.apiv1.entity.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductConverter {

    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .title(request.title())
                .price(request.price())
                .category(request.category())
                .description(request.description())
                .image(request.image())
                .creationDate(LocalDateTime.now())
                .build();

    }

    public ProductResponse toDTO(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getCategory(),
                product.getDescription(),
                product.getImage()
        );
    }

    public ProductRequest toRequest(ProductResponse product) {
        return new ProductRequest(
                product.id(),
                product.title(),
                product.price(),
                product.category(),
                product.description(),
                product.image()
        );
    }

    public List<ProductResponse> toListDTO(List<Product> products) {
        return products.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Product toEntityUpdate(String id, Product entity, ProductResponse response) {
        return Product.builder()
                .id(id)
                .title(response.title() != null ? response.title() : entity.getTitle())
                .price(response.price() != null ? response.price() : entity.getPrice())
                .category(response.category() != null ? response.category() : entity.getCategory())
                .description(response.description() != null ? response.description() : entity.getDescription())
                .image(response.image() != null ? response.image() : entity.getImage())
                .creationDate(entity.getCreationDate())
                .updateDate(LocalDateTime.now())
                .build();

    }
}
