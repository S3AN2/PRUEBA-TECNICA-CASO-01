package com.PruebaTecnica.Caso01.Controller;

import com.PruebaTecnica.Caso01.Entity.TipoCambio;
import com.PruebaTecnica.Caso01.Service.TipoCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controlador para manejar operaciones relacionadas con el tipo de cambio.
 * Proporciona endpoints para obtener, registrar, actualizar y eliminar tipos de cambio.
 */
@RestController
@RequestMapping("/api/TipoCambio")
public class TipoCambioController {

    @Autowired
    private TipoCambioService tipoCambioService;

    /**
     * Método para obtener todos los tipos de cambio.
     *
     * @return un flujo de TipoCambio.
     */
    @GetMapping
    public Flux<TipoCambio> obtenerTodos() {
        return tipoCambioService.obtenerTodos();
    }

    /**
     * Método para buscar un tipo de cambio por su ID.
     *
     * @param id el ID del tipo de cambio a buscar.
     * @return un Mono de TipoCambio.
     */
    @GetMapping("/{id}")
    public Mono<TipoCambio> buscarId(@PathVariable Long id) {
        return tipoCambioService.obtenerId(id);
    }

    /**
     * Método para registrar un nuevo tipo de cambio.
     *
     * @param tipoCambio el tipo de cambio a registrar.
     * @return un Mono de TipoCambio.
     */
    @PostMapping
    public Mono<TipoCambio> registrar(@RequestBody TipoCambio tipoCambio) {
        return tipoCambioService.registrarTipoCambio(tipoCambio);
    }

    /**
     * Método para actualizar un tipo de cambio existente.
     *
     * @param id el ID del tipo de cambio a actualizar.
     * @param tipoCambio el objeto TipoCambio con los datos actualizados.
     * @return un Mono de TipoCambio.
     */
    @PutMapping("/{id}")
    public Mono<TipoCambio> actualizar(@PathVariable Long id, @RequestBody TipoCambio tipoCambio) {
        tipoCambio.setId(id);
        return tipoCambioService.registrarTipoCambio(tipoCambio);
    }

    /**
     * Método para eliminar un tipo de cambio por su ID.
     *
     * @param id el ID del tipo de cambio a eliminar.
     * @return un Mono<Void> indicando que la operación se completó.
     */
    @DeleteMapping("/{id}")
    public Mono<Void> eliminar(@PathVariable Long id) {
        return tipoCambioService.eliminarTipoCambio(id);
    }
}
