package com.example.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "clientId")
    private int clientId;

    @Column(name = "productId")
    private int productId;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "DATEORDER", nullable = false)
    private LocalDateTime dateOrder;


}
