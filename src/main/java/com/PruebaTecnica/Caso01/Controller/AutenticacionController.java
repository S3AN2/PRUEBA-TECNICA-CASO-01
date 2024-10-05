
package com.PruebaTecnica.Caso01.Controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * Controlador para la autenticación de usuarios.
 * Proporciona un endpoint para iniciar sesión y generar un token JWT.
 */
@RestController
@RequestMapping("/api/auth")
public class AutenticacionController {

    private final String SECRET_KEY = "12345";
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    /**
     * Método para iniciar sesión y generar un token JWT.
     *
     * @param username el nombre de usuario para la autenticación.
     * @param password la contraseña del usuario.
     * @return ResponseEntity con un mapa que contiene el token JWT.
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username, @RequestParam String password) {
        // Generar JWT
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
