package com.example.backendprojectmodule_paymentservice.Services;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.param.PaymentLinkCreateParams;
import org.json.JSONObject;

public class StripeGatewayService implements PaymentServiceInterface{
    @Override
    public String createPaymentLink(String orderId) throws StripeException {
        //One way of requesting params through PaymentLinkCreateParams
        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice("price_1MoC3TLkdIwHu7ixcIbKelAC")
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();

        //Another way is through declaring a Map of String to Oject
        JSONObject paymentLinkParams = new JSONObject();

        return null;
    }

    @Override
    public void getPaymentStatus() {

    }

    @Override
    public void handleWebHook() {

    }
}
