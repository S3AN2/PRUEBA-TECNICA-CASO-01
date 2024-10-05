package com.PruebaTecnica.Caso01.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.PruebaTecnica.Caso01.Entity.Usuario;
import com.PruebaTecnica.Caso01.Repository.UsuarioRepository;

import reactor.core.publisher.Mono;

/**
 * Servicio personalizado para la carga de detalles de usuario.
 *
 * Esta clase implementa UserDetailsService para proporcionar la lógica de
 * autenticación al cargar un usuario desde la base de datos.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Carga un usuario por su nombre de usuario.
     *
     * @param username el nombre de usuario a buscar
     * @return un objeto UserDetails que representa al usuario
     * @throws UsernameNotFoundException si no se encuentra el usuario
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Cargar el usuario desde la base de datos
        Mono<Usuario> usuarioMono = usuarioRepository.findByUsername(username);

        Usuario usuario = usuarioMono
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found with username: " + username)))
                .block(); // Bloquea hasta obtener el resultado del Mono

        return usuario; // Devuelve el usuario encontrado
    }
}
