package mk.ukim.finki.emt.productcatalog.config;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.productcatalog.domain.models.Category;
import mk.ukim.finki.emt.productcatalog.domain.models.Product;
import mk.ukim.finki.emt.productcatalog.domain.repository.ProductRepository;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final ProductRepository productRepository;

    @PostConstruct
    public void initData(){
//        Product p1 = Product.of("Tablet 1", Money.of(Currency.MKD, 5000), 0, new Category("Tablets", "Tablets desc"), null);
//        Product p2 = Product.of("Tablet 2", Money.of(Currency.MKD, 6000), 0, new Category("Tablets", "Tablets desc"), null);
//        Product p3 = Product.of("Phone 1", Money.of(Currency.MKD, 10000), 5, new Category("Phone", "Phone desc"), null);

        Product p1 = Product.of("Tablet 1", null, 0, null, null);
        Product p2 = Product.of("Tablet 2", null, 0, null, null);
        Product p3 = Product.of("Phone 1", null, 5, null, null);

        if(productRepository.findAll().isEmpty()){
            productRepository.saveAll(Arrays.asList(p1, p2, p3));
        }

    }
}
