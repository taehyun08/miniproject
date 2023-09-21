package com.model2.mvc.mapper;

import com.model2.mvc.entity.ProductEntity;
import com.model2.mvc.service.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "proTranCode", ignore = true)
    Product productEntityToProduct(ProductEntity entity);

    ProductEntity productToProductEntity(Product product);

    List<Product> productEntitiesToProduct(List<ProductEntity> productEntities);

}
