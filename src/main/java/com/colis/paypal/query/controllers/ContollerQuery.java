package com.colis.paypal.query.controllers;

import com.colis.paypal.query.entity.PaymentEntity;
import com.colis.paypal.query.service.PaymentQueryService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class ContollerQuery{

    private final PaymentQueryService paymentQueryService;

    public ContollerQuery(PaymentQueryService paymentQueryService) {
        this.paymentQueryService = paymentQueryService;
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentEntity> getPayment(@PathVariable String paymentId) throws NotFoundException {
        PaymentEntity paymentDto = paymentQueryService.getPaymentById(paymentId);
        if (paymentDto != null) {
            return ResponseEntity.ok(paymentDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
