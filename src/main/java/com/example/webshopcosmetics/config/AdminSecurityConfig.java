package com.example.webshopcosmetics.config;

import com.example.webshopcosmetics.service.user.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class AdminSecurityConfig {
        @Bean
        PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Autowired
        private DataSource dataSource;

        @Autowired
        private CustomUserDetailsService customUserDetailsService;

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
                authProvider.setUserDetailsService(customUserDetailsService);
                authProvider.setPasswordEncoder(passwordEncoder());

                return authProvider;
        }

        @Bean
        public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/admin/**").authenticated()
                                .anyRequest().permitAll())
                                .formLogin((form) -> form
                                                .loginPage("/admin/sign-in")
                                                .permitAll()
                                                .defaultSuccessUrl("/admin/config-session-user", true)
                                                .failureUrl("/admin/sign-in?error=true")
                                                .loginProcessingUrl("/admin/j_spring_security_check"))
                                .rememberMe(rememberMeConfigurer -> rememberMeConfigurer
                                                .key("uniqueAndSecret")
                                                .tokenValiditySeconds(1209600)
                                )
                                .logout((logout) -> logout
                                                .logoutUrl("/admin/sign-out")
                                                .logoutSuccessUrl("/admin/sign-in?logout=true")
                                                .clearAuthentication(true)
                                                .deleteCookies("JSESSIONID", "remember-me")
                                                .invalidateHttpSession(true)
                                                .permitAll());
                return http.build();
        }
}