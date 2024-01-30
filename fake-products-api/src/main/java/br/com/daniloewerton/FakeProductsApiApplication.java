package br.com.daniloewerton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FakeProductsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakeProductsApiApplication.class, args);
	}

}
