package com.exaltit.kata.adapters.config;

import com.exaltit.kata.KataApplication;
import com.exaltit.kata.adapters.repository.AccountRepository;
import com.exaltit.kata.adapters.repository.OperationRepository;
import com.exaltit.kata.application.service.AccountService;
import com.exaltit.kata.application.service.OperationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = KataApplication.class)
public class BeanConfig {

    @Bean
    AccountService service(AccountRepository repository, OperationRepository operationRepository) {
        return new AccountService(repository, repository, operationRepository);
    }

    @Bean
    OperationService operationService (OperationRepository operationRepository) {
        return new OperationService(operationRepository);
    }
}

