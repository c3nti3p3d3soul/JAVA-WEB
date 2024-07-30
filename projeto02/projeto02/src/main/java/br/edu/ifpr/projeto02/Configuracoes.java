package br.edu.ifpr.projeto02;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

    
@Configuration
@EnableWebSecurity
public class Configuracoes {

    @Autowired
    private FiltroToken filtroToken;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf((csrf) -> csrf.disable).sessionManagement((session) -> session.sessionCreationPolicy(sessionCreationPolicy.STATELESS));
    }
}
