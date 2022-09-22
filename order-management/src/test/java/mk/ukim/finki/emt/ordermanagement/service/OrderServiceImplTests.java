package mk.ukim.finki.emt.ordermanagement.service;

import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.Product;
import mk.ukim.finki.emt.ordermanagement.service.xport.client.ProductClient;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
@SpringBootTest
public class OrderServiceImplTests {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductClient productClient;

    @Test
    public void TestPlaceOrderWithRealData(){
        ProductClient productClient = new ProductClient("http://localhost:9090");
        List<Product> productList = productClient.findAll();
        System.out.println(productList);
    }
}
