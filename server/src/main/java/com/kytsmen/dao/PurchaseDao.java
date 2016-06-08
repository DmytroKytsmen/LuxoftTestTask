package com.kytsmen.dao;

import com.kytsmen.dto.ProductPurchaseDto;

import java.util.List;

public interface PurchaseDao {
    /**
     * @param productPurchase
     */
    void save(ProductPurchaseDto productPurchase);

    /**
     * @param from
     */
    List<ProductPurchaseDto> findByStartDate(long from);
}
