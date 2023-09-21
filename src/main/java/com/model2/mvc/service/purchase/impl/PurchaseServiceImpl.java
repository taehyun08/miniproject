package com.model2.mvc.service.purchase.impl;

import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseRepository;
import com.model2.mvc.service.purchase.PurchaseService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
    //field
    private PurchaseRepository purchaseRepository;
    //constructor
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }


    //method
    @Override
    public Purchase addPurchase(Purchase purchase) throws Exception {
        //dao.insertPurchase(purchase);
        return purchase;
    }

    @Override
    public Purchase getPurchase(int tranNo) throws Exception {
        //return dao.findPurchase(tranNo);
        return null;
    }

    @Override
    public List<Purchase> getPurchaseList(Map<String, Object> map) throws Exception {
        //return dao.getPurchaseList(map);
        return null;
    }

    @Override
    public Purchase updatePurchase(Purchase purchase) throws Exception {
        //dao.updatePurchase(purchase);
        return purchase;
    }

    @Override
    public void updateTranCode(Purchase purchase) throws Exception {
//        dao.updateTranCode(purchase);
    }

    @Override
    public void deletePurchase(int tranNo) throws Exception {
//        dao.deletePurchase(tranNo);
    }

    @Override
    public int getTotalCount(String userId) throws Exception {
//        return dao.getTotalCount(userId);
        return 0;
    }

}