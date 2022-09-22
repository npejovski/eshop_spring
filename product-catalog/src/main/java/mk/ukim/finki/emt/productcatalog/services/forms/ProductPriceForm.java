package mk.ukim.finki.emt.productcatalog.services.forms;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ProductPriceForm {
    @NotNull
    private Money price;
    @NotNull
    private Money clubPrice;
    @NotNull
    private LocalDate date;
}
