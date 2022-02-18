package com.senyasas.cs.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

//@Order(2)
@Configuration
@EnableWebSecurity
public class BasicWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${gifted.security.username}")
    private char[] username;

    @Value("${gifted.security.password}")
    private char[] password;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //Should set this properly instead of disabling
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/actuator/prometheus").permitAll()
                .anyRequest().hasAuthority("ROLE_USER");
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        final var passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        final var userDetails = User.builder()
                .username(new String(username))
                .password(new String(password))
                .roles("USER")
                .passwordEncoder(passwordEncoder::encode)
                .build();

        Arrays.fill(password, (char) 0);

        return new InMemoryUserDetailsManager(userDetails);
    }
}