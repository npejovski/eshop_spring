package mk.ukim.finki.emt.sharedkernel.domain.base;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.util.ProxyUtils;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
@Embeddable         // Bidejki treba da se embedira vo drugite klasi
@Getter
public class DomainObjectId implements Serializable {  // Bidejki ke treba da se serijalizira podocna vo baza
    private String id;

    protected DomainObjectId(@NonNull String uuid){
        this.id = Objects.requireNonNull(uuid, "uuid must not be null");
    }

    public DomainObjectId() {

    }

    @NonNull
    public static <ID extends DomainObjectId> ID randomId(@NonNull Class<ID> idClass) {
        Objects.requireNonNull(idClass, "idClass must not be null");
        try {
            return idClass.getConstructor(String.class).newInstance(UUID.randomUUID().toString());
        } catch (Exception ex) {
            throw new RuntimeException("Could not create new instance of " + idClass, ex);
        }
    }


}
