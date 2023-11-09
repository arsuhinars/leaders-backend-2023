package org.catncode.leaders_backend.security.config;

import org.catncode.leaders_backend.security.dto.AccountRoleAuthority;
import org.catncode.leaders_backend.security.service.AccountDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
    private final AccountDetailService detailService;

    public SecurityConfig(AccountDetailService detailService) {
        this.detailService = detailService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(SecurityConfig::configureHttpRequests)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    private static void configureHttpRequests(
            AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry
    ) {
        var adminAuthority = AccountRoleAuthority.adminAuthority().getAuthority();
        var managerAuthority = AccountRoleAuthority.managerAuthority().getAuthority();
        var employeeAuthority = AccountRoleAuthority.employeeAuthority().getAuthority();

        registry
                .requestMatchers(HttpMethod.GET, "/status").permitAll()

                .requestMatchers(HttpMethod.POST, "/accounts").hasAuthority(adminAuthority)
                .requestMatchers(HttpMethod.GET, "/accounts/*").hasAuthority(adminAuthority)
                .requestMatchers(HttpMethod.PUT, "/accounts/*").hasAuthority(adminAuthority)
                .requestMatchers(HttpMethod.DELETE, "/accounts/*").hasAuthority(adminAuthority)
                .requestMatchers(HttpMethod.GET, "/accounts/login/*").hasAuthority(adminAuthority)
                .requestMatchers(HttpMethod.PUT, "/accounts/login/*").hasAuthority(adminAuthority)
                .requestMatchers(HttpMethod.DELETE, "/accounts/login/*").hasAuthority(adminAuthority)
                .requestMatchers(HttpMethod.PUT, "/accounts/*/password").hasAuthority(adminAuthority)
                .requestMatchers(HttpMethod.GET, "/accounts/all").hasAuthority(adminAuthority)
                .requestMatchers(HttpMethod.GET, "/accounts/current").authenticated()

                .requestMatchers(HttpMethod.POST, "/agent_points").hasAnyAuthority(adminAuthority, managerAuthority)
                .requestMatchers(HttpMethod.GET, "/agent_points/all").hasAnyAuthority(adminAuthority, managerAuthority)
                .requestMatchers(HttpMethod.GET, "/agent_points/*").hasAnyAuthority(adminAuthority, managerAuthority)
                .requestMatchers(HttpMethod.PUT, "/agent_points/*").hasAnyAuthority(adminAuthority, managerAuthority)
                .requestMatchers(HttpMethod.DELETE, "/agent_points/*").hasAnyAuthority(adminAuthority, managerAuthority)

                .requestMatchers(HttpMethod.GET, "/tasks/*/manual").hasAnyAuthority(adminAuthority, managerAuthority)

                .requestMatchers(HttpMethod.GET, "/employees/all").hasAnyAuthority(adminAuthority, managerAuthority)
                .requestMatchers(HttpMethod.GET, "/employees/current").hasAuthority(employeeAuthority)
                .requestMatchers(HttpMethod.GET, "/employees/*").hasAnyAuthority(adminAuthority, managerAuthority)
                .requestMatchers(HttpMethod.PUT, "/employees/*").hasAuthority(adminAuthority)

                .requestMatchers(HttpMethod.POST, "/tasks/generate").hasAnyAuthority(adminAuthority, managerAuthority)
                .requestMatchers(HttpMethod.POST, "/tasks").hasAnyAuthority(adminAuthority, managerAuthority)
                .requestMatchers(HttpMethod.GET, "/tasks/all").authenticated()
                .requestMatchers(HttpMethod.GET, "/tasks/*").hasAnyAuthority(adminAuthority, managerAuthority)
                .requestMatchers(HttpMethod.PUT, "/tasks/*").hasAnyAuthority(adminAuthority, managerAuthority)
                .requestMatchers(HttpMethod.DELETE, "/tasks/*").hasAnyAuthority(adminAuthority, managerAuthority)
                .requestMatchers(HttpMethod.GET, "/tasks/*/status").authenticated()
                .requestMatchers(HttpMethod.PUT, "/tasks/*/status").hasAuthority(employeeAuthority)

                .anyRequest().authenticated();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(detailService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
