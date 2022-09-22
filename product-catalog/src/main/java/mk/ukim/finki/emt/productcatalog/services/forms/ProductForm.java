package mk.ukim.finki.emt.productcatalog.services.forms;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProductForm {
    @NotNull
    private String productName;
    @NotNull
    private Money price;
    @NotNull
    private int sales;
    @NotNull
    private CategoryForm category;

    @Valid
    @NotEmpty
    private List<ProductPriceForm> productPrices;

}
