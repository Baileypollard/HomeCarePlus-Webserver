package com.techprimers.security.securitydbexample;

import com.techprimers.security.securitydbexample.model.CustomUserDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application
{

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
}
