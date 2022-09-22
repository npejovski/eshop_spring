package mk.ukim.finki.emt.productcatalog.domain.models;

import lombok.Getter;
import mk.ukim.finki.emt.productcatalog.domain.valueobjects.Quantity;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="product")
//@Getter
public class Product extends AbstractEntity<ProductId> {
    private String productName;
    private int sales = 0;
//    private Quantity quantity;  // Za ova ni e potreben vrednosen objekt, bidejki imame biznis logika povrzana so ovaa vrednost.
    @AttributeOverrides({       // Ova e korisno ako imame 2 Money, da ne nastanat problemi so 2x 2 koloni so isti iminja.
            @AttributeOverride(name = "amount", column = @Column(name = "price_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "price_currency"))
    })
    private Money price;        // Isto i tuka.
    @ManyToOne(fetch = FetchType.LAZY)      // Default is EAGER.
    private Category category;
    @OneToMany
    private List<ProductPrice> productPrices;

    public Product() {
        super(ProductId.randomId(ProductId.class));
    }



    public static Product of(String productName, Money price, int sales, Category category, List<ProductPrice> productPrices){
        Product p = new Product();
        p.productName = productName;
        p.price = price;
        p.sales = sales;
        p.category = category;
        p.productPrices = productPrices;
        return p;
    }

    public void addSales(int qty){
        this.sales += qty;
    }

    public void removeSales(int qty){
        this.sales -= qty;
    }
}
