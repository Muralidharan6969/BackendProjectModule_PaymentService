package com.example.backendprojectmodule_paymentservice.Services;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public interface PaymentServiceInterface {
    public String createPaymentLink(String orderId) throws StripeException, RazorpayException;
    public void getPaymentStatus();
    public void handleWebHook();
}
