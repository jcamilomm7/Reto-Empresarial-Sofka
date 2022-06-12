package co.com.sofka.questions.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import reactor.core.publisher.Mono;

@Configuration
public class WebSecurityConfigurationFLux {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    SecurityWebFilterChain springWebFilter(ServerHttpSecurity http) {

        http.oauth2ResourceServer()
                .jwt();
        return http
                .authorizeExchange()
                .pathMatchers(HttpMethod.GET,"/getAll").permitAll()
                .anyExchange().authenticated()
                .and().build();





/*

        return http.cors().disable()
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() -> {
                    swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                })).accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> {
                    swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                })).and()
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers(HttpMethod.GET,"/getAll").permitAll()
                .anyExchange().authenticated()
                .and()
                .build();
*/

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
