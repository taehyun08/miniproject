package com.example.jpaTest;

import com.model2.mvc.common.Search;
import com.model2.mvc.entity.ProductEntity;
import com.model2.mvc.entity.QProductEntity;
import com.model2.mvc.mapper.ProductMapper;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductRepository;
import com.model2.mvc.service.product.ProductService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@SpringBootTest
class ProductJpaApplicationTest {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    //@Test
    public void testProductJpa(){
        Product product = Product.builder().prodNo(10).prodName("orp").build();
        ProductEntity productEntity = productMapper.productToProductEntity(product);
        System.out.println(productEntity);
        System.out.println(productMapper.productEntityToProduct(productEntity));
    }

    //@Test
    public void testProductReposi(){
        Product product = Product.builder().prodNo(10).prodName("orp").build();
        ProductEntity productEntity = productMapper.productToProductEntity(product);
        productRepository.save(productEntity);
    }

    //@Test
    public void testInsert(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            ProductEntity productEntity = ProductEntity.builder()
                    .fileName("filee")
                    .price(100)
                    .stock(55)
                    .prodDetail("싸요")
                    .views(0)
                    .prodName("improductName"+i)
                    .build();
            productRepository.save(productEntity);
        });

    }

    //@Test
    public void testSearch(){
        Pageable pageable = PageRequest.of(3,10);

        QProductEntity qProductEntity = QProductEntity.productEntity;

        String keyword = "3";

        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = qProductEntity.prodName.contains(keyword);
        expression.and(qProductEntity.prodNo.between(10,50));


        builder.and(expression);
        Page<ProductEntity> result = productRepository.findAll(builder, pageable);

        result.stream().forEach(System.out::println);

    }

    //@Test
    public void testFindByName(){
        Pageable pageable = PageRequest.of(1,10);
        Page<ProductEntity> result =  productRepository.findByProdNameContaining("1", pageable);
        //result.
        result.stream().forEach(System.out::println);
    }

    //@Test
    public void testEntitiesToDto(){
//        Pageable pageable = PageRequest.of(1,10);
//        Page<ProductEntity> result =  productRepository.findByProdNameContaining("1", pageable);
//        List<Product> products = productMapper.productEntitiesToProduct(result.getContent());
//        products.forEach(System.out::println);
    }

    @Test
    public void testGetProductList() throws Exception{
        Search search = Search.builder()
                .orderBy("prodNo")
                .pageUnit(5)
                .currentPage(1)
                .build();
        Map<String, Object> map =  productService.getProductList(search);
        System.out.println(map);
    }

}

