package com.kytsmen.dao;

public class DaoConstants {

    /**
     * Queries
     */
    public static final String PURCHASE_SAVE_QUERY = "INSERT INTO purchase (quantity, purchasedate, product) VALUES (%s, %s, (SELECT id FROM product WHERE name = '%s'))";
    public static final String PURCHASE_REPORT_QUERY = "SELECT name, quantity, quantity*price as sum FROM purchase a JOIN product b ON a.product=b.id WHERE purchasedate >= %s";
    public static final String PRODUCT_BY_NAME_QUERY = "SELECT * FROM product WHERE name = '%s'";
    public static final String PRODUCT_SELECT_ALL = "SELECT * FROM product";

    /**
     * Product fields
     */
    public static final String PRODUCT_ID_FIELD = "id";
    public static final String PRODUCT_PRICE_FIELD = "price";
    public static final String PRODUCT_PURCHASE_COUNT_FIELD = "quantity";
    public static final String PRODUCT_NAME_FIELD = "name";
    public static final String PRODUCT_PURCHASE_SUM_FIELD = "sum";


}
