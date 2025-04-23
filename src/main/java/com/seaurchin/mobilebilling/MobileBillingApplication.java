package com.seaurchin.mobilebilling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(
		title = "Mobile Billing API",
		version = "v1",

		description = "API documentation for the Mobile Billing System"
))


@SpringBootApplication
public class MobileBillingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileBillingApplication.class, args);
	}

}
