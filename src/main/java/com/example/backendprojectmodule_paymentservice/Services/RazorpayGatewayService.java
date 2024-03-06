package com.example.backendprojectmodule_paymentservice.Services;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RazorpayGatewayService implements PaymentServiceInterface{
    private RazorpayClient razorpayClient;

    @Autowired
    public RazorpayGatewayService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }
    @Override
    public String createPaymentLink(String orderId) throws StripeException, RazorpayException {

        //Creating the Payment Link Request with basic details
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",false);
//        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",System.currentTimeMillis() + (15 * 60 * 1000));
        paymentLinkRequest.put("reference_id", orderId);
        paymentLinkRequest.put("description","Payment for order no " + orderId);

        //Creating the customer object to be added to the payment link request
        //We have the get the user details with the help of the order id
        JSONObject customer = new JSONObject();
        customer.put("name","+919999999999");
        customer.put("contact","Gaurav Kumar");
        customer.put("email","gaurav.kumar@example.com");
        paymentLinkRequest.put("customer",customer);

        //Creating the notify object to be added to the payment link request
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("reminder_enable",true);

        //Creating the notes object to be added to the payment link request
        JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);

        //Creating the callback object to be added to the payment link request
        paymentLinkRequest.put("callback_url","https://muralidharan.tech/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return payment.get("short_url");
    }

    @Override
    public void getPaymentStatus() {

    }

    @Override
    public void handleWebHook() {

    }
}
