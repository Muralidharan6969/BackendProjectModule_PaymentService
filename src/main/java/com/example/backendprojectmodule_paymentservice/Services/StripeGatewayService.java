package com.example.backendprojectmodule_paymentservice.Services;

import com.stripe.Stripe;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Primary
public class StripeGatewayService implements PaymentServiceInterface{
    private StripeClient stripeClient;

    @Autowired
    public StripeGatewayService(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @Override
    public String createPaymentLink(String orderId) throws StripeException {
        ProductCreateParams productParams =
                ProductCreateParams.builder().setName(orderId).build();
        Product product = stripeClient.products().create(productParams);

        PriceCreateParams priceParams =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(1000L)
                        .setProduct(product.getId())
                        .build();
        Price price = stripeClient.prices().create(priceParams);

        PaymentLinkCreateParams paymentLinkParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("https://muralidharan.tech/")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();
        PaymentLink paymentLink = stripeClient.paymentLinks().create(paymentLinkParams);
        return paymentLink.getUrl();
    }

    @Override
    public void getPaymentStatus() {

    }

    @Override
    public void handleWebHook() {

    }
}
