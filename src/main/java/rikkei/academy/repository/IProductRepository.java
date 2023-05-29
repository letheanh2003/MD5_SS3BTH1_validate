package rikkei.academy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rikkei.academy.model.Product;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findAllByNameContaining(String name, Pageable pageable);
    @Query("SELECT p FROM Product AS p WHERE p.name LIKE CONCAT('%',:name,'%')")
    Page<Product> findByNameProduct(@Param("name") String name, Pageable pageable);
}
