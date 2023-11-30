package com.colis.paypal.query.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {
    @Id
    private String paymentId;

    private double price;

    private String currency;

    private String method;

    private String intent;

    private String description;

    private String cancelUrl;

    private String successUrl;


}