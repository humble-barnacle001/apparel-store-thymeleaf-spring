package com.barnacle.apparel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/search", "/orders", "/buy/**").authenticated()
                .antMatchers("/dashboard").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/auth").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .headers().httpStrictTransportSecurity()
                .includeSubDomains(true).maxAgeInSeconds(24 * 3600);
    }

    // TODO: USE THE USER DETAILS FROM DB

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("test@tester.co")
                .password("6e2TF9f2kiGQR8v")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
