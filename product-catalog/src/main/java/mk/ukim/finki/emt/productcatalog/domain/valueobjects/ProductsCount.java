package mk.ukim.finki.emt.productcatalog.domain.valueobjects;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class ProductsCount implements ValueObject {
    private final int productsCount;

    protected ProductsCount() {     // Just to satisfy the compailer.
        this.productsCount = 0;
    }

    public ProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }
}
