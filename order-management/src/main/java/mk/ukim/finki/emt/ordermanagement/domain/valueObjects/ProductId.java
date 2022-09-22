package mk.ukim.finki.emt.ordermanagement.domain.valueObjects;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ProductId extends DomainObjectId {
    // Ovie konstruktori ne morashe, jas gi dodadov za da testiram kako raboti mapiranjeto na JSON. Od string -> ProductId. Nejci samo da si mapira.
    public ProductId(){

    }

    public ProductId(String uuid){
        super(uuid);
    }
}
