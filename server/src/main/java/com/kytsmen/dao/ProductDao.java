package com.kytsmen.dao;

import com.kytsmen.dto.ProductDto;

import java.util.List;

public interface ProductDao {
    /**
     *
     * @param name
     * @return
     */
    ProductDto findByName(String name);

    /**
     *
     * @return
     */
    List<ProductDto> findAll();
}
