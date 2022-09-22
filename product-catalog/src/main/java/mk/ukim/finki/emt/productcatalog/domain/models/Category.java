package mk.ukim.finki.emt.productcatalog.domain.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    // Another way @Column(nullable = false). No so @NotNull e podobro bidejki prvo pravi validacija, pa potoa pravi sql query. Shto znaci ako se prati null Hibernate ke go fati problemot, i nema da napravi sql query [No potrebno e: hibernate.validator.apply_to_ddl property = true]
    private String name;        // A so nullable = false, ke izvrshi sql query i ke proba vo baza da vnesi null vo not null mesto i ke padni tamu, naemsto tuka. No i za ova ima chare, mozhe da se aktivira validator -> [spring.jpa.properties.hibernate.check_nullability=true].
    @Column(length = 4000)
    private String description;

    public Category(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Category() {
    }
}
