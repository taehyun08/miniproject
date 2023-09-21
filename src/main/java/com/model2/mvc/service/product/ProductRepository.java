package com.model2.mvc.service.product;

import com.model2.mvc.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface ProductRepository extends JpaRepository<ProductEntity, Integer>, QuerydslPredicateExecutor<ProductEntity> {
    Page<ProductEntity> findByProdNameContaining(String prdName, Pageable pageable);
}
