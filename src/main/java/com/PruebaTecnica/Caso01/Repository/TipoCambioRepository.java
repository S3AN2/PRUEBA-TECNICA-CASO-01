package com.PruebaTecnica.Caso01.Repository;

import com.PruebaTecnica.Caso01.Entity.TipoCambio;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
/**
 * Repositorio para la entidad TipoCambio.
 * Proporciona métodos para realizar operaciones CRUD de manera reactiva.
 */
public interface TipoCambioRepository extends ReactiveCrudRepository<TipoCambio, Long> {
}
