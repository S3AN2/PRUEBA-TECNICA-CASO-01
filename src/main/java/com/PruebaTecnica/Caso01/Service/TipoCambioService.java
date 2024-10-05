package com.PruebaTecnica.Caso01.Service;

import com.PruebaTecnica.Caso01.Entity.TipoCambio;
import com.PruebaTecnica.Caso01.Repository.TipoCambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

/**
 * Implementación del servicio de tipos de cambio.
 *
 * Esta clase implementa la interfaz ITipoCambioService y proporciona
 * la lógica de negocio para manejar tipos de cambio.
 */
@Service
public class TipoCambioService implements ITipoCambioService {

    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    /**
     * Registra un nuevo tipo de cambio.
     *
     * @param tipoCambio el tipo de cambio a registrar
     * @return un Mono que contiene el tipo de cambio registrado
     */
    @Override
    public Mono<TipoCambio> registrarTipoCambio(TipoCambio tipoCambio) {
        tipoCambio.setFecha(LocalDateTime.now()); // Establecer la fecha actual
        return tipoCambioRepository.save(tipoCambio); // Guardar el tipo de cambio
    }

    /**
     * Obtiene todos los tipos de cambio.
     *
     * @return un Flux que contiene todos los tipos de cambio
     */
    @Override
    public Flux<TipoCambio> obtenerTodos() {
        return tipoCambioRepository.findAll(); // Devolver todos los tipos de cambio
    }

    /**
     * Actualiza un tipo de cambio existente.
     *
     * @param id el ID del tipo de cambio a actualizar
     * @param tipoCambio el objeto TipoCambio con los nuevos valores
     * @return un Mono que contiene el tipo de cambio actualizado
     */
    @Override
    public Mono<TipoCambio> actualizarTipoCambio(Long id, TipoCambio tipoCambio) {
        return tipoCambioRepository.findById(id) // Buscar el tipo de cambio por ID
                .flatMap(existingTipoCambio -> {
                    // Actualizar los valores del tipo de cambio
                    existingTipoCambio.setMonedaOrigen(tipoCambio.getMonedaOrigen());
                    existingTipoCambio.setMonedaDestino(tipoCambio.getMonedaDestino());
                    existingTipoCambio.setMonto(tipoCambio.getMonto());
                    existingTipoCambio.setTipoCambio(tipoCambio.getTipoCambio());
                    return tipoCambioRepository.save(existingTipoCambio); // Guardar el tipo de cambio actualizado
                });
    }

    /**
     * Elimina un tipo de cambio por su ID.
     *
     * @param id el ID del tipo de cambio a eliminar
     * @return un Mono vacío al completarse la eliminación
     */
    @Override
    public Mono<Void> eliminarTipoCambio(Long id) {
        return tipoCambioRepository.deleteById(id); // Eliminar el tipo de cambio por ID
    }

    /**
     * Obtiene un tipo de cambio por su ID.
     *
     * @param id el ID del tipo de cambio a buscar
     * @return un Mono que contiene el tipo de cambio encontrado o vacío si no se encuentra
     */
    @Override
    public Mono<TipoCambio> obtenerId(Long id) {
        return tipoCambioRepository.findById(id); // Buscar el tipo de cambio por ID
    }
}
