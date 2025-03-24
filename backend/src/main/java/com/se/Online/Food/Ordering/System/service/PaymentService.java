package com.se.Online.Food.Ordering.System.service;

import com.se.Online.Food.Ordering.System.dto.PaymentDTO;

public interface PaymentService {
    PaymentDTO createPayment(PaymentDTO paymentDTO);

    PaymentDTO getPaymentByOrderId(Long orderId);
}
