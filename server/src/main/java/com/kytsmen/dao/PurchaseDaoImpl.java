package com.kytsmen.dao;

import com.kytsmen.dto.ProductDto;
import com.kytsmen.dto.ProductPurchaseDto;
import com.kytsmen.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.init.ScriptStatementFailedException;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.kytsmen.dao.DaoConstants.*;

@Repository
public class PurchaseDaoImpl implements PurchaseDao {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductDao productDao;

    @Override
    public void save(ProductPurchaseDto productPurchase) {
        ProductDto productDto = productDao.findByName(productPurchase.getProduct());
        try {
            jdbcTemplate.execute(String.format(PURCHASE_SAVE_QUERY, productPurchase.getCount(), System.currentTimeMillis(), productPurchase.getProduct()));
        } catch (QueryTimeoutException e) {
            throw new DaoException(ServiceErrorCode.GENERAL_ERROR, String.format("Can't save product with name %s", productPurchase.getProduct()));
        }
    }

    @Override
    public List<ProductPurchaseDto> findByStartDate(long from) {
        List<ProductPurchaseDto> productPurchaseDto = null;
        try {
            productPurchaseDto = jdbcTemplate.query(String.format(PURCHASE_REPORT_QUERY, from), new PurchaseMapper());
            LOG.debug("Purchase: {}", productPurchaseDto);
        } catch (ScriptStatementFailedException e) {
            throw new DaoException(ServiceErrorCode.GENERAL_ERROR, String.format("Failure while query execution"));
        }
        return productPurchaseDto;
    }

    private static final class PurchaseMapper implements RowMapper<ProductPurchaseDto> {

        @Override
        public ProductPurchaseDto mapRow(ResultSet resultSet, int i) throws SQLException {
            ProductPurchaseDto productPurchase = new ProductPurchaseDto();
            productPurchase.setCount(resultSet.getInt(PRODUCT_PURCHASE_COUNT_FIELD));
            productPurchase.setProduct(resultSet.getString(PRODUCT_NAME_FIELD));
            productPurchase.setSum(resultSet.getDouble(PRODUCT_PURCHASE_SUM_FIELD));
            return productPurchase;
        }
    }
}
