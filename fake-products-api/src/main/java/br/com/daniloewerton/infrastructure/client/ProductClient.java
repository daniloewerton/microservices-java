package br.com.daniloewerton.infrastructure.client;

import br.com.daniloewerton.apiv1.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "products-api", url = "${fake-api.url:#{null}}")
public interface ProductClient {

    @GetMapping("/products")
    List<ProductResponse> getProducts();
}
