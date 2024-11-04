package com.example.paymentservice.services;

//import com.scaler.paymentservicefinal.services.paymentgateway.PaymentGateway;
import com.example.paymentservice.paymentgateway.PaymentGateway;
import com.example.paymentservice.paymentgateway.StripePaymentGateway;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private StripePaymentGateway stripePaymentGateway;

    public PaymentService(StripePaymentGateway stripePaymentGateway ) {
        this.stripePaymentGateway = stripePaymentGateway;
    }

    public String initiatePayment(String orderId) throws StripeException {
    //public String initiatePayment(String orderId, String email, String phoneNumber, Long amount) throws StripeException {
        // Order order = orderService.getOrderDetails(orderId)
        // Long amount = order.getAmount();
        // double amount = 10.10; // store number * 100
        // String orderId, String email, String phoneNumber, Long amount
//        Long amount = 1010L; // 10.00 => 1000


        return stripePaymentGateway.generatePaymentLink(10000L ,orderId);
    }
}
