package mk.ukim.finki.emt.productcatalog.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class ProductId extends DomainObjectId {
    public ProductId(){
        super(ProductId.randomId(ProductId.class).getId());
    }

    public ProductId(@NonNull String uuid){
        super(uuid);
    }

    public static ProductId of(String uuid){
        return new ProductId(uuid);
    }
}
