package mk.ukim.finki.emt.productcatalog.services.forms;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryForm {
    @NotNull
    private String name;
    @NotNull
    private String description;
}
