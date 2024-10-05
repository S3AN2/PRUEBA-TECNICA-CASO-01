package com.PruebaTecnica.Caso01.Service;

import com.PruebaTecnica.Caso01.Entity.TipoCambio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interfaz que define los servicios relacionados con el tipo de cambio.
 *
 * Esta interfaz contiene métodos para registrar, obtener, actualizar y eliminar tipos de cambio.
 */
public interface ITipoCambioService {

    /**
     * Registra un nuevo tipo de cambio.
     *
     * @param tipoCambio el tipo de cambio a registrar
     * @return un Mono que contiene el tipo de cambio registrado
     */
    Mono<TipoCambio> registrarTipoCambio(TipoCambio tipoCambio);

    /**
     * Obtiene todos los tipos de cambio.
     *
     * @return un Flux que contiene todos los tipos de cambio
     */
    Flux<TipoCambio> obtenerTodos();

    /**
     * Actualiza un tipo de cambio existente.
     *
     * @param id el ID del tipo de cambio a actualizar
     * @param tipoCambio el objeto TipoCambio con los nuevos valores
     * @return un Mono que contiene el tipo de cambio actualizado
     */
    Mono<TipoCambio> actualizarTipoCambio(Long id, TipoCambio tipoCambio);

    /**
     * Elimina un tipo de cambio por su ID.
     *
     * @param id el ID del tipo de cambio a eliminar
     * @return un Mono vacío al completarse la eliminación
     */
    Mono<Void> eliminarTipoCambio(Long id);

    /**
     * Obtiene un tipo de cambio por su ID.
     *
     * @param id el ID del tipo de cambio a buscar
     * @return un Mono que contiene el tipo de cambio encontrado o vacío si no se encuentra
     */
    Mono<TipoCambio> obtenerId(Long id);
}
