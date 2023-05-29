package rikkei.academy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import rikkei.academy.model.Product;

public interface IProductService {
    Page<Product> findAll(Pageable pageable);
    void save(Product product);
    Page<Product> findAllByNameContaining(String name, Pageable pageable);
    Page<Product> findByNameProduct(@Param("name") String name, Pageable pageable);
}
