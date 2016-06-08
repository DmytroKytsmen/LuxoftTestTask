package com.kytsmen.controller;

import com.kytsmen.dao.ProductDao;
import com.kytsmen.dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDto> getProducts() {
        return productDao.findAll();
    }

}
