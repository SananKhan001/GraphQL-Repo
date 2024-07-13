package com.GraphQL.learn.GraphQL.artifact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true) // To specify that we are controlling security on method level
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager users(){
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("sanan")
                .password("sanan")
                .authorities("admin").build();

        UserDetails userDetails1 = User.withDefaultPasswordEncoder()
                .username("khan")
                .password("khan")
                .authorities("user").build();

        return new InMemoryUserDetailsManager(userDetails, userDetails1);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/graphiql/**", "/custompath/**")
                .permitAll()
                .and()
                .formLogin()
                .successForwardUrl("/graphiql");
        return http.build();
    }

}
