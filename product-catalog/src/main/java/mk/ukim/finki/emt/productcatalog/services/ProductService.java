package mk.ukim.finki.emt.productcatalog.services;

import mk.ukim.finki.emt.productcatalog.domain.models.Product;
import mk.ukim.finki.emt.productcatalog.domain.models.ProductId;
import mk.ukim.finki.emt.productcatalog.services.forms.ProductForm;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(ProductId productId);
    Product createProduct(ProductForm form);
    Product orderItemCreated(ProductId productId, int quantity);
    Product orderItemRemoved(ProductId productId, int quantity);
}
