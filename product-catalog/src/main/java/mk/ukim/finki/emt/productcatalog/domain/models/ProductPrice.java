package mk.ukim.finki.emt.productcatalog.domain.models;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "product_prices")
@Data
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // TODO ID MANY TO ONE
    @ManyToOne
    private Product product;
    private Money price;
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "club_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "club_amount"))
    })
    private Money clubPrice;        // Club price.
    private LocalDate date;

    public ProductPrice() {
    }

    public ProductPrice(Money money, Money clubMoney, LocalDate date) {
        this.price = money;
        this.clubPrice = clubMoney;
        this.date = date;
    }
}
