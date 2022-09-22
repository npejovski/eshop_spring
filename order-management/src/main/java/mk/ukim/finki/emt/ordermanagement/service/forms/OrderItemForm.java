package mk.ukim.finki.emt.ordermanagement.service.forms;

import lombok.Data;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.Product;

import javax.validation.constraints.Min;

@Data   // Anemichnost.
public class OrderItemForm {
    @NonNull
    private Product product;

    @Min(1)
    private int quantity = 1;
}
