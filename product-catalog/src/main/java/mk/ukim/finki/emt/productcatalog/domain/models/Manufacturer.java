package mk.ukim.finki.emt.productcatalog.domain.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "manufacturers")
@Data
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;

    public Manufacturer(String name){
        this.name = name;
    }

    protected Manufacturer() {
    }
}
