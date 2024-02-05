package br.com.daniloewerton.business;

import br.com.daniloewerton.apiv1.dto.ProductResponse;
import br.com.daniloewerton.apiv1.exception.FakeStoreException;
import br.com.daniloewerton.infrastructure.client.ProductClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FakeStoreService {

    private final ProductClient productClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public List<ProductResponse> getFromFakeStoreApi() {
        log.info("c=FakeStoreService :: m=getFromFakeStoreApi :: Chamando a fake Store Api.");
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("fake-store-circuitbreaker");
        return circuitBreaker.run(productClient::getProducts,
                throwable -> fallback());
    }

    private List<ProductResponse> fallback() {
        throw new FakeStoreException("Fake Store Api indisponível");
    }
}
