package mk.ukim.finki.emt.ordermanagement.domain.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Getter
public class Product implements ValueObject {
    private final ProductId id;
    private final String name;
    private final Money price;
    private final int sales;

    private Product(){
        this.id = ProductId.randomId(ProductId.class);
        this.name = "";
        this.price = new Money(Currency.MKD, 0);
        this.sales = 0;
    }

//    @JsonCreator          // Ne ni mora.
    public Product(@JsonProperty("id") ProductId id,
                   @JsonProperty("productName") String name,
                   @JsonProperty("price") Money price,
                   @JsonProperty("sales") int sales) {
        System.out.println("CALLED!");
        this.id = id;
        this.name = name;
        this.price = price;
        this.sales = sales;
    }


}

// Notice:
// @JsonCreator - Ako nekoj od parametrite nema @JsonProperty, togash Spring/Java ke frli error koga ke proba da go mapira preku
// ParameterizedTypeReference. Bidejki e kako overloaded, i toj sho go bara preku ParameterizedTypeReference ne e ovoj shto nie sakame da se iskoristi, fakticki ne go najduva soodvetniot i pagja.
// Vsushnost ParameterizedTypeReference saka site parametri da mu gi oznachis so @JsonProperty i metodot so @JsonCreator.
// Correction: Bitno e samo parametrite da bidat oznacheni so @JsonProperty, a metodot ne e potrebno.