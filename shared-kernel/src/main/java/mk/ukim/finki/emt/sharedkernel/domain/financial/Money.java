package mk.ukim.finki.emt.sharedkernel.domain.financial;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable // Go objasnav ova vo Quantity. So ovaa anotacija ovaj atribut vo bilo koja klasa kade shto ke go koristime ke bidi kako svoj "primitiven atribut" koj shto ke vlezi vo tabelata vo baza.
@Getter
public class Money implements ValueObject {
//    @Column(name = "price_currency")
    @Enumerated(value = EnumType.STRING)    // Deka vo tabelata ovoj atribut treba da se zapishe kako string.
    private final Currency currency;
    private final double amount;

    protected Money(){  // Just to satisfy the compailer.
        this.currency = null;
        this.amount = 0;
    }

    public Money(@NonNull Currency currency, @NonNull double amount){
        this.currency = currency;
        this.amount = amount;
    }

    public static Money of(Currency currency, double amount){
        return new Money(currency, amount);
    }

    public Money valueOf(Currency currency, double amount){
        return new Money(currency, amount);
    }

    public Money add(Money money){
        if(!currency.equals(money.getCurrency()))
            throw new IllegalArgumentException("Cannot add two Money with different currencies.");

        return new Money(currency, amount + money.amount);
    }

    public Money subtract(Money money){
        if(!currency.equals(money.getCurrency()))
            throw new IllegalArgumentException("Cannot add two Money with different currencies.");

        return new Money(currency, amount - money.amount);
    }

    public Money multiply(int m){
        return new Money(currency, amount * m);
    }
}
