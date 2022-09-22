package mk.ukim.finki.emt.productcatalog.domain.valueobjects;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;

@Embeddable // Za da mozhe lesno da se vgradi vo samiot entitet. Odnosno, site atributi shto gi ima Quantity, da gi ima i samata tabela Product kade shto go koristime ovaj value object.
            // Ako ne e Embeddable, JPA ke ni se buni, ke ni bara nekakva one to many vrska ili nesh slicno. A vaka ke si go tretira kako da e vnatre vo sebe.
@Getter
public class Quantity implements ValueObject {
    private final int quantity;

    protected Quantity(){       // To make the compailer happy. This will never be used.
        this.quantity = 0;
    }
}


// Note: Site atributi na value objects treba da se immutable, final.