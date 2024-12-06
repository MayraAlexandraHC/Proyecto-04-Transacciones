package com.bank.TransaccionesMs.service;

import com.bank.TransaccionesMs.model.Cuenta;
import reactor.core.publisher.Mono;

public interface CuentaService {
    /**
     * Obtener detalles de la cuenta por ID
     * @param idCuenta El ID de la cuenta
     * @return Detalles de la cuenta
     */
    Mono<Cuenta> obtenerCuenta(Integer idCuenta);

    /**
     * Realizar un depósito en la cuenta
     * @param idCuenta El ID de la cuenta
     * @param monto El monto a depositar
     * @return La cuenta actualizada
     */
    Mono<Cuenta> depositarEnCuenta(Integer idCuenta, Double monto);

    /**
     * Realizar un retiro de la cuenta
     * @param idCuenta El ID de la cuenta
     * @param monto El monto a retirar
     * @return La cuenta actualizada
     */
    Mono<Cuenta> retirarDeCuenta(Integer idCuenta, Double monto);
}