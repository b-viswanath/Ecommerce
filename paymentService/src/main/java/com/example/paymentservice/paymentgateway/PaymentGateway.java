package com.example.paymentservice.paymentgateway;

import com.stripe.exception.StripeException;

public interface PaymentGateway {
    //String generatePaymentLink(String orderId, String email, String phoneNumber, Long amount);
    String generatePaymentLink(Long amount,String orderId) throws StripeException;

}
