package com.bank.TransaccionesMs.service.impl;

import com.bank.TransaccionesMs.client.CuentaClient;
import com.bank.TransaccionesMs.model.Cuenta;
import com.bank.TransaccionesMs.service.CuentaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    private final CuentaClient cuentaClient;

    @Override
    public Mono<Cuenta> obtenerCuenta(Integer idCuenta) {
        log.info("Servicio: Obteniendo cuenta con ID: {}", idCuenta);
        return cuentaClient.obtenerCuenta(idCuenta)
                .doOnSuccess(cuenta -> log.info("Servicio: Cuenta obtenida exitosamente: {}", idCuenta))
                .doOnError(error -> log.error("Servicio: Error al obtener cuenta: {}", error.getMessage()));
    }

    @Override
    public Mono<Cuenta> depositarEnCuenta(Integer idCuenta, Double monto) {
        log.info("Servicio: Iniciando depósito de {} en cuenta {}", monto, idCuenta);
        return cuentaClient.depositarEnCuenta(idCuenta, monto)
                .doOnSuccess(cuenta -> log.info("Servicio: Depósito realizado exitosamente en cuenta: {}", idCuenta))
                .doOnError(error -> log.error("Servicio: Error al realizar depósito: {}", error.getMessage()));
    }

    @Override
    public Mono<Cuenta> retirarDeCuenta(Integer idCuenta, Double monto) {
        log.info("Servicio: Iniciando retiro de {} de cuenta {}", monto, idCuenta);
        return cuentaClient.retirarDeCuenta(idCuenta, monto)
                .doOnSuccess(cuenta -> log.info("Servicio: Retiro realizado exitosamente de cuenta: {}", idCuenta))
                .doOnError(error -> log.error("Servicio: Error al realizar retiro: {}", error.getMessage()));
    }
}
