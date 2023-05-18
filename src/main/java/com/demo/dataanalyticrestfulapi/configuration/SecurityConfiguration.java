package com.demo.dataanalyticrestfulapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // Create three type of beans
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/authentication/**","/api/v1/file/**","api/v1/mail/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        return http.build();
    }

    // postgres, testing ( H2 database . also in memory database)

//    1. user credentials

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        // create three users
        // user
        UserDetails user1 = User.builder().username("Samnang").roles("USER").password("12345").build();
        // admin
        UserDetails user2 = User.builder().username("Dara").roles("USER").password("12345").build();
        // admin
        UserDetails user3 = User.builder().username("Nara").roles("USER").password("12345").build();

        return new InMemoryUserDetailsManager(user1,user2,user3);
    }
//    2. password encoder
//    BCrypt

//    No Ops
    @SuppressWarnings("deprecation")
    @Bean
    public NoOpPasswordEncoder passwordEncoder(){
        return (NoOpPasswordEncoder)  NoOpPasswordEncoder.getInstance();
    }
//    3. security filter-chain



}
