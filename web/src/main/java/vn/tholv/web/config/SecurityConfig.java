package vn.tholv.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChan(HttpSecurity http) throws Exception {
        http.csrf(cors -> {cors.ignoringRequestMatchers(requestMatcher -> {return requestMatcher.getServletPath().startsWith("/api");});});
        http.csrf(csrf -> {csrf.ignoringRequestMatchers(requestMatcher -> {return requestMatcher.getServletPath().startsWith("/api");});});
        http.formLogin(formLogin -> formLogin.disable());
        http.logout(logout -> logout.disable());
        http
                .securityMatcher("/api/admin/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();

    }

}
