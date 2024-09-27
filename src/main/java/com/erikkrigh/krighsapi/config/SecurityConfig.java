package com.erikkrigh.krighsapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthConverter jwtAuthConverter;

    @Bean
    protected SessionAuthenticationStrategy strategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest()
                        .authenticated());

        httpSecurity
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthConverter)));

        httpSecurity
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //allt efter inloggning i h2-console blir tomt utan nedan kod, då iframe blir blockerat
        httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .headers(httpSecurityHeadersConfigurer -> {
                    httpSecurityHeadersConfigurer.frameOptions(frameOptionsConfig -> {
                        frameOptionsConfig.disable();
                    });
                });

        return httpSecurity.build();
    }


//BASIC SECURITY
/*    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        UserDetails erik = User.builder()
                .username("erik")
                .password("{noop}test")
                .roles("USER", "ADMIN")
                .build();

        UserDetails eriknoadmin = User.builder()
                .username("eriknoadmin")
                .password("{noop}test")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(erik, eriknoadmin);
    }

    @Bean
    public SecurityFilterChain securityFilterchain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/admin/members").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/admin/members/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/admin/members").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/admin/members").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/admin/members/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/mypages/members").hasRole("MEMBER")
                        .requestMatchers(HttpMethod.PUT, "/mypages/members/**").hasRole("MEMBER")

        );
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }*/
}