package br.com.daniloewerton.repository;

import br.com.daniloewerton.apiv1.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    boolean existsByTitle(String title);
    Product findByTitle(String title);

    @Transactional
    void deleteByTitle(String title);
}
