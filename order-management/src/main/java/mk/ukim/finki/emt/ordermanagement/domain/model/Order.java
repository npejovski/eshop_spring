package mk.ukim.finki.emt.ordermanagement.domain.model;

import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.Product;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class  Order extends AbstractEntity<OrderId> {

    private Instant orderedOn;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Column(name="order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

//    @Transient                        >> We have method for this.
//    private Money total;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)    // 1: Ako se izbirshi narchata, da se izbrishat site orderItems shto i pripagaat. 2:  It marks "child" entity to be removed when it's no longer referenced from the "parent" entity. OrphanRemoval tells the ORM that if I remove an Item object from the collection of Items that belong to an Invoice object (in memory operation), and then "save" the Invoice, the removed Item should be deleted from the underlying DB
                                                                                                                                                                                  // Ova go razbiram vaka: Ako izbrisham neshto od ovoj set<OrderItems> i napravam save, da se izbrishi soodvetniot orderItem.
    private Set<OrderItem> orderItemList;

    public Order() {
        super(OrderId.randomId(OrderId.class));
    }

    public Order(Instant orderedOn, Currency currency){
        super(OrderId.randomId(OrderId.class));
        this.orderedOn = orderedOn;
        this.currency = currency;
    }



    public Money total(){
        return orderItemList.stream().map(OrderItem::subtotal).reduce(new Money(currency, 0), Money::add);
    }

    public OrderItem addItem(@NonNull Product product, int qty){
        Objects.requireNonNull(product, "product must not be null.");
        var item = new OrderItem(product.getPrice(), qty, product.getId());
        orderItemList.add(item);
        return item;
    }

    public void removeItem(@NonNull OrderItemId orderItemId){
        Objects.requireNonNull(orderItemId, "OrderItemId must not be null");
        orderItemList.removeIf(e -> e.getProductId().equals(orderItemId));
    }
}
