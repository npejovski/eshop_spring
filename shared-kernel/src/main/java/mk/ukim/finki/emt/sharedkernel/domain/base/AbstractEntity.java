package mk.ukim.finki.emt.sharedkernel.domain.base;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.util.ProxyUtils;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass   // Letting JPA know that this is superclass when it creates its stuff
@Getter
public class AbstractEntity <ID extends DomainObjectId>{
    @EmbeddedId     // Bidejki e kompozitno pole (Ke bide kompoziten kluc). Bidejki mozhe vnatre sodrzhi poveke elementi.Bidejki ne e poveke primitiva - id, tuku e klasa.
    private ID id;

    protected AbstractEntity(@NonNull AbstractEntity<ID> source){
        this.id = source.id;
    }

    protected AbstractEntity(@NonNull ID id){
        this.id = Objects.requireNonNull(id, "id must not be null");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !getClass().equals(ProxyUtils.getUserClass(obj))) {
            return false;
        }

        var other = (AbstractEntity<?>) obj;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : id.hashCode();
    }

}
