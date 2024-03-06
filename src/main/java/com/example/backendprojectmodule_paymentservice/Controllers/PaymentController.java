package com.example.backendprojectmodule_paymentservice.Controllers;

import com.example.backendprojectmodule_paymentservice.DTO.PaymentDTO;
import com.example.backendprojectmodule_paymentservice.Services.PaymentServiceInterface;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private PaymentServiceInterface paymentService;

    @Autowired
    public PaymentController(PaymentServiceInterface paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("")
    public String createPaymentLink(@RequestBody PaymentDTO paymentDTO) throws StripeException, RazorpayException {
        // Create payment link
        return paymentService.createPaymentLink(paymentDTO.getOrderId());
    }

    public void getPaymentStatus() {
        // Get payment status
    }

    public void handleWebHook() {
        // Handle web hook
    }
}
