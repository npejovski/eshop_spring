package mk.ukim.finki.emt.productcatalog.xports.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.productcatalog.domain.models.Product;
import mk.ukim.finki.emt.productcatalog.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductResource {
    private final ProductService productService;

    @GetMapping
    public List<Product> findAll(){
        System.out.println("???1");
        return productService.findAll();
    }


}
