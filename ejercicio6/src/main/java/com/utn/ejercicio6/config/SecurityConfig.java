package com.utn.ejercicio6.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Endpoints públicos
                        .requestMatchers("/api/auth/**").permitAll()
                        // Solo MEDICO y ADMIN pueden listar turnos
                        .requestMatchers(HttpMethod.GET, "/api/turnos").hasAnyRole("MEDICO", "ADMIN")
                        // Solo PACIENTE puede crear un turno
                        .requestMatchers(HttpMethod.POST, "/api/turnos").hasRole("PACIENTE")
                        // ADMIN o MEDICO pueden intentar borrar (la lógica fina va en @PreAuthorize)
                        .requestMatchers(HttpMethod.DELETE, "/api/turnos/**").hasAnyRole("ADMIN", "MEDICO")
                // Solo PACIENTE puede ver su propio perfil
                .requestMatchers(HttpMethod.GET, "/api/pacientes/me").hasRole("PACIENTE")
//                 Todo lo demás requiere autenticación
                .anyRequest().authenticated()
 );
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}