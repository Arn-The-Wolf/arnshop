package com.arnshop.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Person customer;

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    @Builder.Default
    private Double totalAmount = 0.0;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();

    @Column(nullable = false)
    @Builder.Default
    private String status = "PENDING";

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column
    private LocalDateTime shippedDate;

    @Column
    private LocalDateTime deliveredDate;

    @Column(nullable = false)
    private String shippingAddress;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    @Builder.Default
    private String paymentStatus = "PENDING";

    @PrePersist
    protected void onCreate() {
        orderDate = LocalDateTime.now();
        if (orderNumber == null) {
            orderNumber = "ORD-" + System.currentTimeMillis();
        }
    }

    public void addItem(String itemName, Double price, Integer quantity) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        OrderItem item = new OrderItem(itemName, price, quantity);
        items.add(item);
        this.totalAmount = (this.totalAmount == null ? 0.0 : this.totalAmount) + (price * quantity);
    }

    public void removeItem(OrderItem item) {
        if (items != null) {
            items.remove(item);
            this.totalAmount -= item.getPrice() * item.getQuantity();
        }
    }
}

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class OrderItem {
    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;
}
