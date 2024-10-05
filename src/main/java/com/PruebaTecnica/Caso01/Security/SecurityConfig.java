package com.PruebaTecnica.Caso01.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Configuración de seguridad para la aplicación utilizando Spring WebFlux Security.
 *
 * Esta clase define la configuración de seguridad, incluyendo la autorización de rutas,
 * desactivación de CSRF y la incorporación de un filtro JWT.
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private JwtFiltro jwtFiltro;

    /**
     * Configura el filtro de seguridad de la aplicación.
     *
     * @param http el objeto ServerHttpSecurity que permite configurar la seguridad
     * @return el objeto SecurityWebFilterChain configurado
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(authorize -> authorize
                        .pathMatchers("/api/auth/login").permitAll() // Permitir acceso a la ruta de login
                        .pathMatchers("/api/exchange/**").authenticated() // Proteger las rutas bajo /api/exchange/
                        .anyExchange().permitAll() // Permitir todas las demás rutas
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // Desactivar CSRF
                .addFilterBefore(jwtFiltro, SecurityWebFiltersOrder.AUTHENTICATION); // Añadir el filtro JWT en el orden correcto

        return http.build(); // Construir el objeto SecurityWebFilterChain
    }
}
