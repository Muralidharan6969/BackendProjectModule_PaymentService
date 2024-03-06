package com.example.backendprojectmodule_paymentservice.Configurations;

import com.stripe.StripeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${stripe.api_key}")
    private String stripeKey;

    @Bean
    public StripeClient createStripeClient() {
        return new StripeClient(stripeKey);
    }
}
