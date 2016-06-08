import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kytsmen.Application;
import com.kytsmen.dao.PurchaseDao;
import com.kytsmen.dto.ProductDto;
import com.kytsmen.dto.ProductPurchaseDto;
import com.kytsmen.dto.PurchaseDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class PurchaseControllerTest {

    @Autowired
    private PurchaseDao purchaseDao;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private ObjectMapper jsonMapper = new ObjectMapper();

    private Integer months = 5;
    private String GET_PURCHASE_URL = "/api/purchase?months=" + months.toString();
    private String MAKE_PURCHASE_URL = "/api/purchase";
    private String GET_PRODUCTS_URL = "/api/products";

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void makePurchaseTest() throws Exception {
        mockMvc.perform(post(MAKE_PURCHASE_URL).contentType(MediaType.APPLICATION_JSON).content(getPurchaseJson()))
                .andExpect(status().isOk())
                .andReturn();

        List<ProductPurchaseDto> productPurchaseList = purchaseDao.findByStartDate(months);
        assertNotNull(productPurchaseList);
        assertTrue(productPurchaseList.contains(getProductPurchase()));
    }

    @Test
    public void getPurchaseTest() throws Exception {
        purchaseDao.save(getProductPurchase());
        MvcResult mvcResult = mockMvc.perform(get(GET_PURCHASE_URL))
                .andExpect(status().isOk())
                .andReturn();

        PurchaseDto returnedPurchase = getPurchaseFromResult(mvcResult);
        Assert.assertEquals(getPurchase(), returnedPurchase);
    }

//    @Test
//    public void getProductsTest() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(get(GET_PRODUCTS_URL))
//                .andExpect(status().isOk())
//                .andReturn();
//        ProductDto returnedProducts = getProductsFromResult(mvcResult);
//        Assert.assertEquals(getAvailableProducts(), returnedProducts);
//    }
//
//    private ProductDto getAvailableProducts() {
//        ProductDto product = new ProductDto();
//        product.setName("prod1");
//        product.setPrice(20.50);
//        product.setId(1);
//        return product;
//    }
//
//    private ProductDto getProductsFromResult(MvcResult mvcResult) throws IOException {
//        String json = mvcResult.getResponse().getContentAsString();
//        ProductDto productDto = jsonMapper.readValue(json, ProductDto.class);
//        return productDto;
//    }


    private ProductPurchaseDto getProductPurchase() {
        ProductPurchaseDto productPurchase = new ProductPurchaseDto();
        productPurchase.setSum(2060250);
        productPurchase.setProduct("prod1");
        productPurchase.setCount(100500);
        return productPurchase;
    }

    private PurchaseDto getPurchase() {
        return new PurchaseDto(Arrays.asList(getProductPurchase()));
    }

    private PurchaseDto getPurchaseFromResult(MvcResult mvcResult) throws IOException {
        String json = mvcResult.getResponse().getContentAsString();
        PurchaseDto purchase = jsonMapper.readValue(json, PurchaseDto.class);
        return purchase;
    }

    private String getPurchaseJson() throws JsonProcessingException {
        return jsonMapper.writeValueAsString(getPurchase());
    }
}
