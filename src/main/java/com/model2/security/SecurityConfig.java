package com.model2.security;

import jakarta.servlet.DispatcherType;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("----filterChain----");

        http
                .authorizeHttpRequests(authorize -> authorize
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers(
                                new AntPathRequestMatcher("/static/**"),
                                new AntPathRequestMatcher("/user/addUser"),
                                new AntPathRequestMatcher("/images/**"),
                                new AntPathRequestMatcher("/css/**"),
                                new AntPathRequestMatcher("/javascript/**")).permitAll()
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(login -> login
                        .loginPage("/user/login")
                        .loginProcessingUrl("/user/login")
                        .usernameParameter("userId")
                        .passwordParameter("password")
//                        .defaultSuccessUrl("/")
                        .defaultSuccessUrl("/", true) // 왜 true하는 지 모르겠어서 잠시 빼둠
                        .permitAll()
                )
                .logout(Customizer.withDefaults());




        return http.build();

    }

}
