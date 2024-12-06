package com.bank.TransaccionesMs.service;

import com.bank.TransaccionesMs.model.Transaccion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransaccionService {
    /**
     * Procesa un depósito en una cuenta
     * @param transaccion La transacción a procesar
     * @return La transacción procesada
     */
    Mono<Transaccion> procesarDeposito(Transaccion transaccion);

    /**
     * Procesa un retiro de una cuenta
     * @param transaccion La transacción a procesar
     * @return La transacción procesada
     */
    Mono<Transaccion> procesarRetiro(Transaccion transaccion);

    /**
     * Procesa una transferencia entre cuentas
     * @param transaccion La transacción a procesar
     * @return La transacción procesada
     */
    Mono<Transaccion> procesarTransferencia(Transaccion transaccion);

    /**
     * Obtiene el historial de transacciones de una cuenta
     * @param idCuenta El ID de la cuenta
     * @return Lista de transacciones de la cuenta
     */
    Flux<Transaccion> obtenerHistorialTransacciones(Integer idCuenta);
}