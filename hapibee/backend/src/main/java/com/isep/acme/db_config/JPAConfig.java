package com.isep.acme.db_config;

import com.isep.acme.repositories.*;
import com.isep.acme.repositories.IRepos.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("JPA")
public class JPAConfig {

    @Bean
    @Primary
    public IProductRepository mongoProductRepository(){
        return new JPAProductRepository();
    }

    @Bean
    @Primary
    public IUserRepository mongoUserRepository(){
        return new JPAUserRepository();
    }
}
