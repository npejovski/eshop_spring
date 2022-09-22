package mk.ukim.finki.emt.ordermanagement.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.ordermanagement.domain.exceptions.OrderIdDoesNotExist;
import mk.ukim.finki.emt.ordermanagement.domain.exceptions.OrderItemIdDoesNotExist;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderItemId;
import mk.ukim.finki.emt.ordermanagement.domain.repository.OrderRepository;
import mk.ukim.finki.emt.ordermanagement.service.OrderService;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderItemForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final Validator validator;

    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm, "orderForm must not be null.");
        var constraintViolations = validator.validate(orderForm);
        if(constraintViolations.size() > 0)
            throw new ConstraintViolationException("The orderForm is not valid.", constraintViolations);
        Order newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        return newOrder.getId();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdDoesNotExist {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(OrderIdDoesNotExist::new);
        order.addItem(orderItemForm.getProduct(), orderItemForm.getQuantity());
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdDoesNotExist, OrderItemIdDoesNotExist {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(OrderIdDoesNotExist::new);
        order.removeItem(orderItemId);
        orderRepository.saveAndFlush(order);
    }


    private Order toDomainObject(OrderForm orderForm){
        Order order = new Order(Instant.now(), orderForm.getCurrency());
        orderForm.getItems().forEach(i -> order.addItem(i.getProduct(), i.getQuantity()));
        return order;
    }
}
