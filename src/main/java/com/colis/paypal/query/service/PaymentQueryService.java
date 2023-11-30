package com.colis.paypal.query.service;

import com.colis.paypal.query.entity.PaymentEntity;
import com.colis.paypal.query.repositories.PaymentRepository;
import org.springframework.stereotype.Service;
import javassist.NotFoundException;



import java.util.List;

@Service
public class PaymentQueryService {
    private final PaymentRepository paymentRepository;

    public PaymentQueryService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<PaymentEntity> getAllPayments() {
        return paymentRepository.findAll();
    }

    public PaymentEntity getPaymentById(String paymentId) throws NotFoundException {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new NotFoundException("Payment not found"));
    }
}

