package com.mfs.merchantQR;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableAsync
//@EnableTransactionManagement
//@EnableJpaRepositories
@OpenAPIDefinition(info = @Info(title = "MerchantQr" , version = "1.0", description = "Merchant QR"))
//@ComponentScan("com.mfs.merchantQR.*")
public class MerchantQrApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MerchantQrApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MerchantQrApplication.class);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
