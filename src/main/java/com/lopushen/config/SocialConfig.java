package com.lopushen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import javax.sql.DataSource;

@Configuration
public class SocialConfig {

    @Value("${spring.social.facebook.app-id}")
    private String appId;
    @Value("${spring.social.facebook.app-secret}")
    private String appSecret;

    @Autowired
    private DataSource dataSource;

    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(new FacebookConnectionFactory(appId, appSecret));

        return registry;
    }


    @Bean
    public UsersConnectionRepository usersConnectionRepository() {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator(), Encryptors.noOpText());
        repository.setConnectionSignUp(new FacebookConnectionSignup());
        return repository;
    }
}
