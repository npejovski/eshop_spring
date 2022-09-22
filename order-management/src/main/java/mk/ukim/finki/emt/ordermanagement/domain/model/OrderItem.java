package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.ProductId;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_items")
@Getter
public class OrderItem extends AbstractEntity<OrderItemId> {
    private Money itemPrice;
    @Column(name = "qty", nullable = false)
    private int quantity;
    @AttributeOverride(name="id", column = @Column(name = "product_id", nullable = false)) // so name="id" kazhuvame koj atribut od ProductId klasata da se override, mozhe da ima poveke promenlivi, pa zatoa naglasuvame koja promenliva vo sho -> @column(...).
    private ProductId productId;

    public OrderItem(Money itemPrice, int quantity, @NonNull ProductId productId) {
        super(DomainObjectId.randomId(OrderItemId.class));
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.productId = productId;
    }

    public OrderItem() {
        super(OrderItemId.randomId(OrderItemId.class));
    }

    public Money subtotal(){
        return itemPrice.multiply(quantity);
    }

}
