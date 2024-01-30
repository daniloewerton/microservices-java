package br.com.daniloewerton.business;

import br.com.daniloewerton.apiv1.dto.ProductResponse;
import br.com.daniloewerton.infrastructure.client.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductClient productClient;

    public List<ProductResponse> getProducts() {
        return productClient.getProducts();
    }
}
