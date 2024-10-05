package com.PruebaTecnica.Caso01.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad que representa un tipo de cambio.
 * Contiene información sobre las monedas y el tipo de cambio correspondiente.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TipoCambio {
    @Id
    private Long id; // ID del tipo de cambio
    private String monedaOrigen; // Moneda de origen
    private String monedaDestino; // Moneda de destino
    private BigDecimal tipoCambio; // Tipo de cambio
    private BigDecimal monto; // Monto original
    private BigDecimal montoConvertido; // Monto convertido
    private LocalDateTime fecha; // Fecha de la operación
    private String usuarioId; // ID del usuario que realiza la operación
}
