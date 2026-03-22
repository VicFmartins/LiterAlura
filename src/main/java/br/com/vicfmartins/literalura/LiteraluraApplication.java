package br.com.vicfmartins.literalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class LiteraluraApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Bean
    RestClient gutendexRestClient(RestClient.Builder builder) {
        return builder
                .baseUrl("https://gutendex.com")
                .build();
    }
}
