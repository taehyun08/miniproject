package com.model2.mvc.service.product.impl;

import com.model2.mvc.common.Search;
import com.model2.mvc.mapper.ProductMapper;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductRepository;
import com.model2.mvc.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Product addProduct(Product product) throws Exception {
        productRepository.save(productMapper.productToProductEntity(product));
        return product;
    }

    @Override
    public Product getProduct(int prod_no) throws Exception {
//        return productRepository.findById(prod_no);
        return null;
    }

    @Override
    public Map<String, Object> getProductList(Search search) throws Exception {
        Pageable pageable = PageRequest.of(1,10);
        //Page<Product> p = productRepository.findByProdNameContaining(search.getSearchKeyword(), pageable);
        Map<String, Object> map = new HashMap<>();
        //map.put("list", productDao.getProductList(search));
//        map.put("count", productDao.getTotalCount(search));
        return map;
    }

    @Override
    public int updateProduct(Product product) throws Exception {
//        return productDao.updateProduct(product);
        return 0;
    }

    @Override
    public List<String> getProductListName() throws Exception {
//        return productDao.getProductListName();
        return null;
    }



}