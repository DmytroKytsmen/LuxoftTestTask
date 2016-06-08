package com.kytsmen.dao;

import com.kytsmen.dto.ProductDto;
import com.kytsmen.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.kytsmen.dao.DaoConstants.*;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ProductDto findByName(String name) {
        try {
            ProductDto product = jdbcTemplate.queryForObject(String.format(PRODUCT_BY_NAME_QUERY, name), new ProductMapper());
            return product;
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException(ServiceErrorCode.ITEM_NOT_FOUND, String.format("Product with name %s is not found", name));
        } catch (QueryTimeoutException e) {
            throw new DaoException(ServiceErrorCode.INVALID_ARGUMENTS, String.format("Query with %s product name can't be executed", name));
        }
    }

    @Override
    public List<ProductDto> findAll() {
        return jdbcTemplate.query(PRODUCT_SELECT_ALL, new ProductMapper());
    }

    private static final class ProductMapper implements RowMapper<ProductDto> {

        @Override
        public ProductDto mapRow(ResultSet resultSet, int i) throws SQLException {
            ProductDto product = new ProductDto();
            product.setId(resultSet.getInt(DaoConstants.PRODUCT_ID_FIELD));
            product.setName(resultSet.getString(PRODUCT_NAME_FIELD));
            product.setPrice(resultSet.getDouble(PRODUCT_PRICE_FIELD));
            return product;
        }
    }
}
