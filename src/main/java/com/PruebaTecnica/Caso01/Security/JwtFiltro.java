package com.PruebaTecnica.Caso01.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * Filtro JWT que valida el token JWT en las solicitudes.
 *
 * Este filtro extrae el token de autorización de las cabeceras, valida el token
 * y establece el contexto de seguridad para el usuario autenticado.
 */@Component
@Order(1)
public class JwtFiltro implements WebFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    private final String SECRET_KEY = "12345";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        final String authorizationHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String username = null;
        String jwt = null;

        // Verificar si la cabecera contiene el token Bearer
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Extraer el token JWT sin el prefijo "Bearer "

            try {
                // Intentar parsear el JWT
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(jwt) // Parsear el JWT
                        .getBody();

                username = claims.getSubject(); // Obtener el nombre de usuario del JWT
            } catch (io.jsonwebtoken.MalformedJwtException e) {
                // El token está mal formado
                System.out.println("El token JWT está mal formado: " + e.getMessage());
                return Mono.empty(); // Detener la cadena si el token es inválido
            } catch (io.jsonwebtoken.ExpiredJwtException e) {
                // El token ha expirado
                System.out.println("El token JWT ha expirado: " + e.getMessage());
                return Mono.empty(); // Detener la cadena si el token ha expirado
            } catch (Exception e) {
                // Cualquier otro tipo de excepción
                System.out.println("Error al procesar el token JWT: " + e.getMessage());
                return Mono.empty(); // Detener la cadena si hay algún otro problema
            }
        }

        // Si se ha extraído un nombre de usuario y no hay autenticación previa, autenticar al usuario
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Crear un token de autenticación para el usuario
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // Establecer la autenticación en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // Continuar con la cadena de filtros
        return chain.filter(exchange);
    }
}
