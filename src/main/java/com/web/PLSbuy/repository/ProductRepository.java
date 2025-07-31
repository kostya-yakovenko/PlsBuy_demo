package com.web.PLSbuy.repository;

import com.web.PLSbuy.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitleContainingAndCity(String title, String city);

    // Поиск по части названия
    List<Product> findByTitleContaining(String title);

    // Поиск по городу
    List<Product> findByCity(String city);
    List<Product> findByTitle(String title);
}
