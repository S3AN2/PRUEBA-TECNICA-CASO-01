package com.PruebaTecnica.Caso01.Repository;

import com.PruebaTecnica.Caso01.Entity.Usuario;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * Repositorio reactivo para la entidad Usuario.
 *
 * Esta interfaz extiende ReactiveCrudRepository para proporcionar operaciones CRUD
 * asíncronas sobre la entidad Usuario.
 */
public interface UsuarioRepository extends ReactiveCrudRepository<Usuario, Long> {

    /**
     * Encuentra un usuario por su nombre de usuario.
     *
     * @param username el nombre de usuario a buscar
     * @return un Mono que contiene el usuario encontrado o vacío si no se encuentra
     */
    Mono<Usuario> findByUsername(String username);
}
