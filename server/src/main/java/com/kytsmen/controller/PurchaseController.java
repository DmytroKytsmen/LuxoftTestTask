package com.kytsmen.controller;

import com.kytsmen.dao.PurchaseDao;
import com.kytsmen.dto.ProductPurchaseDto;
import com.kytsmen.dto.PurchaseDto;
import com.kytsmen.exception.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PurchaseController {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseController.class);

    @Autowired
    private PurchaseDao purchaseDao;

    @ExceptionHandler(DaoException.class)
    public void handleException(DaoException ex, HttpServletResponse response) {
        try {
            switch (ex.getErrorCode()) {
                // TODO: extend exception to error code mapping
                case ITEM_NOT_FOUND:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, ex.getMessage());
                    break;
                case BAD_REQUEST_PARAMS:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                    break;
                case GENERAL_ERROR:
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
                    break;
                case INVALID_ARGUMENTS:
                    response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, ex.getMessage());
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            LOG.error("Can't handle exception", e);
        }
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public void makePurchase(@RequestBody PurchaseDto purchase) {
        LOG.debug("Make purchase: [{}]", purchase);
        for (ProductPurchaseDto productPurchase : purchase.getData()) {
            purchaseDao.save(productPurchase);
        }
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.GET)
    public PurchaseDto getPurchaseReport(@RequestParam("months") Integer months) {
        List<ProductPurchaseDto> productPurchaseList = purchaseDao.findByStartDate(getTimestampMonthsAgo(months));
        LOG.debug("Retrieved purchase list: {}", productPurchaseList);
        return new PurchaseDto(productPurchaseList);
    }


    private long getTimestampMonthsAgo(int months) {
        int years = months / 12;
        months %= 12;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -years);
        calendar.add(Calendar.MONTH, -months);
        return calendar.getTimeInMillis();
    }
}
