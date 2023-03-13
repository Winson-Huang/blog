package org.winson.blog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    public static final String AUTHORITY_ROLE_PREFIX = "ROLE_";
    public static final String AUTHORITY_ROLE = "USER";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(16);
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authConfig
    ) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //         .authorizeRequests()
    //             .antMatchers("/api/**")
    //             .hasRole(AUTHORITY_ROLE)
    //             .antMatchers("/**")
    //             .permitAll()
            
    //         .and()
    //         .logout()

    //         ;

    //     return http.build();
    // }

}
