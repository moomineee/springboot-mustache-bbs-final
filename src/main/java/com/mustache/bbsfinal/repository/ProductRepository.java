package com.mustache.bbsfinal.repository;

import com.mustache.bbsfinal.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
