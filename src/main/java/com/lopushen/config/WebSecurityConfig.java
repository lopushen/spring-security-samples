package com.lopushen.config;

import com.lopushen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;

import javax.sql.DataSource;
import java.util.Arrays;

@EnableWebSecurity
@Configuration
/**
 * Give the config the very high priority
 */
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserService userService;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private FacebookConnectionSignup facebookConnectionSignup;

    private static final String USERS_QUERY = "select username,password,enabled from users where username=?";
    private static final String ROLES_QUERY = "select username,role from roles where username=?";

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(USERS_QUERY).authoritiesByUsernameQuery(ROLES_QUERY);
        auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource(contextSource())
                .passwordCompare()
                .passwordEncoder(new LdapShaPasswordEncoder())
                .passwordAttribute("userPassword");

        auth.userDetailsService(userService);
    }

    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        return new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:8389/"), "dc=springframework,dc=org");
    }

//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        userDetailsManager.createUser(User.withUsername("Anna").password("11111").roles("USER").build());
//        userDetailsManager.createUser(User.withUsername("Ievgenii").password("55555").roles("USER,ADMIN").build());
//        return userDetailsManager;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests().antMatchers("/index.html", "/home.html", "/login.html", "/")
                .permitAll().anyRequest().authenticated().and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    @Bean
    public ProviderSignInController providerSignInController() {
        ((JdbcUsersConnectionRepository) usersConnectionRepository)
                .setConnectionSignUp(facebookConnectionSignup);

        return new ProviderSignInController(
                connectionFactoryLocator,
                usersConnectionRepository,
                new FacebookSignInAdapter());
    }
}
