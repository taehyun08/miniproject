package com.model2.mvc.service.purchase;

import com.model2.mvc.entity.PurchaseEntity;
import com.model2.mvc.service.domain.Purchase;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

//@Mapper
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Integer>, QuerydslPredicateExecutor<PurchaseEntity> {

}