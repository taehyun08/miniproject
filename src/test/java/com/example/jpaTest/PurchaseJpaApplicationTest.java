package com.example.jpaTest;

import com.model2.mvc.common.Search;
import com.model2.mvc.entity.ProductEntity;
import com.model2.mvc.entity.PurchaseEntity;
import com.model2.mvc.entity.UserEntity;
import com.model2.mvc.mapper.PurchaseMapper;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductRepository;
import com.model2.mvc.service.purchase.PurchaseRepository;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class PurchaseJpaApplicationTest {
    @Autowired
    PurchaseMapper purchaseMapper;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PurchaseService purchaseService;

    //@Test
    public void testBinding(){
        Product product = Product.builder()
                .prodName("improduct")
                .prodNo(10)
                .price(5000)
                .build();
        User user = User.builder()
                .userId("imUserId")
                .password("impassword")
                .userName("nnn")
                .build();
        PurchaseEntity purchaseEntity = purchaseMapper.purchaseToPurchaseEntity(Purchase.builder()
                .purchaseProd(product)
                .buyer(user)
                .build());
        System.out.println(purchaseEntity);
        System.out.println(purchaseMapper.purchaseEntityToPurchase(purchaseEntity));
    }

    //@Test
    public void insertTest(){
        IntStream.rangeClosed(1,10).forEach(i -> {
            UserEntity user = UserEntity.builder().userName("nnaammee").userId("user" + i).password("1111").build();
            userRepository.save(user);

            ProductEntity product = ProductEntity.builder().prodNo(i+100).build();
            productRepository.save(product);

            PurchaseEntity purchase = PurchaseEntity.builder().productEntity(product).userEntity(user).build();
            purchaseRepository.save(purchase);
        });
    }


    //@Test
    public void mapperTest(){
        UserEntity user = UserEntity.builder().userName("nnaammee").userId("user123").password("1111").build();
        ProductEntity product = ProductEntity.builder().prodNo(100).build();
        PurchaseEntity purchaseEntity = PurchaseEntity.builder().productEntity(product).userEntity(user).build();
        System.out.println(purchaseEntity);
        System.out.println(purchaseMapper.purchaseEntityToPurchase(purchaseEntity));
    }

    //@Test
    //@Transactional
    public void findByIdTest(){
        Optional<PurchaseEntity> optionalPurchase = purchaseRepository.findById(3);
        System.out.println(optionalPurchase.get());

    }

    //@Test
    public void deleteTest(){
        productRepository.deleteById(101);
    }

    @Test
    public void listTest() throws Exception{
        String userId = "user1";
        Search search = Search.builder()
                              .orderBy("tranNo")
                              .currentPage(1)
                              .pageUnit(5)
                              .startRowNum(1)
                              .endRowNum(5)
                              .build();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("search", search);
        System.out.println(purchaseService.getPurchaseList(map));

    }

}
