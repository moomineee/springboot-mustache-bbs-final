package com.mustache.bbsfinal.repository;

import com.mustache.bbsfinal.domain.Hospital;
import com.mustache.bbsfinal.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
