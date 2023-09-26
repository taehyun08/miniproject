package com.model2.mvc.service.purchase.impl;

import com.model2.mvc.common.Search;
import com.model2.mvc.entity.PurchaseEntity;
import com.model2.mvc.mapper.PurchaseMapper;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseRepository;
import com.model2.mvc.service.purchase.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
    //field
    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;
    //method
    @Override
    public Purchase addPurchase(Purchase purchase) throws Exception {
        purchaseRepository.save((purchaseMapper.purchaseToPurchaseEntity(purchase)));
        return purchase;
    }

    @Override
    public Purchase getPurchase(int tranNo) throws Exception {
        Optional<PurchaseEntity> purchaseEntityOptional = purchaseRepository.findById(tranNo);
        return purchaseEntityOptional.map(purchaseMapper::purchaseEntityToPurchase).orElse(null);
    }

    @Override
    public Map<String, Object> getPurchaseList(Map<String, Object> map) throws Exception {
        Search search = (Search) map.get("search");
        String userId = (String) map.get("userId");
        System.out.println(userId);
        Sort sort = Sort.by(search.getOrderBy());
        Pageable pageable = PageRequest.of(search.getCurrentPage(), search.getPageUnit(), sort);
        Page<PurchaseEntity> page = purchaseRepository.findByUserEntity_UserId(userId, pageable);
        System.out.println(page.get().toList());
        Map<String, Object> result = new HashMap<>();
        result.put("list", page.map(purchaseMapper::purchaseEntityToPurchase).toList());
        result.put("count", page.getTotalPages());
        return result;
    }

    @Override
    public Purchase updatePurchase(Purchase purchase) throws Exception {
        if(purchaseRepository.findById(purchase.getTranNo()).isPresent()) {
            purchaseRepository.save(purchaseMapper.purchaseToPurchaseEntity(purchase));
            return purchase;
        }else{
            return null;
        }
    }

    @Override
    public void updateTranCode(Purchase purchase) throws Exception {
//        dao.updateTranCode(purchase);
    }

    @Override
    public void deletePurchase(int tranNo) throws Exception {
//        dao.deletePurchase(tranNo);
    }


}