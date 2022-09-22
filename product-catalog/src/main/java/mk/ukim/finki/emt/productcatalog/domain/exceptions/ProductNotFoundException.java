package mk.ukim.finki.emt.productcatalog.domain.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String productId){
        super(String.format("Product with id: %s not found.", productId));
    }
}
