package mk.ukim.finki.emt.productcatalog.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.productcatalog.domain.exceptions.ProductNotFoundException;
import mk.ukim.finki.emt.productcatalog.domain.models.Category;
import mk.ukim.finki.emt.productcatalog.domain.models.Product;
import mk.ukim.finki.emt.productcatalog.domain.models.ProductId;
import mk.ukim.finki.emt.productcatalog.domain.models.ProductPrice;
import mk.ukim.finki.emt.productcatalog.domain.repository.ProductRepository;
import mk.ukim.finki.emt.productcatalog.services.ProductService;
import mk.ukim.finki.emt.productcatalog.services.forms.ProductForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl  implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(ProductId productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId.getId()));
    }

    @Override
    public Product createProduct(ProductForm form) {
        Category category = new Category(form.getCategory().getName(), form.getCategory().getDescription());
        List<ProductPrice> productPrices = new ArrayList<>();
        form.getProductPrices().forEach(i -> productPrices.add(new ProductPrice(i.getPrice(), i.getClubPrice(), i.getDate())));

        Product p = Product.of(form.getProductName(), form.getPrice(), form.getSales(), category, productPrices);
        return productRepository.saveAndFlush(p);
    }

    @Override
    public Product orderItemCreated(ProductId productId, int quantity) {
        Product p = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId.getId()));
        p.addSales(quantity);
        productRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public Product orderItemRemoved(ProductId productId, int quantity) {
        Product p = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId.getId()));
        p.removeSales(quantity);
        productRepository.saveAndFlush(p);
        return p;
    }
}
