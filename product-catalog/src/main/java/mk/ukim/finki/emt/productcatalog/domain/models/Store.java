package mk.ukim.finki.emt.productcatalog.domain.models;

import com.sun.istack.NotNull;
import lombok.Data;
import mk.ukim.finki.emt.productcatalog.domain.valueobjects.ProductsCount;

import javax.persistence.*;

@Entity
@Table(name = "stores")
@Data
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;
    private String url;
    private ProductsCount productsCount;
}
