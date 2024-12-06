package com.bank.TransaccionesMs.service.impl;

import com.bank.TransaccionesMs.model.TipoTransaccion;
import com.bank.TransaccionesMs.model.Transaccion;
import com.bank.TransaccionesMs.repository.TransaccionRepository;
import com.bank.TransaccionesMs.service.CuentaService;
import com.bank.TransaccionesMs.service.TransaccionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl implements TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final CuentaService cuentaService;

    @Override
    public Mono<Transaccion> procesarDeposito(Transaccion transaccion) {
        log.info("Procesando depósito para la cuenta: {}", transaccion.getIdCuentaOrigen());
        transaccion.setTipo(TipoTransaccion.DEPOSITO);
        transaccion.setFecha(LocalDateTime.now());

        return cuentaService.depositarEnCuenta(
                        Integer.valueOf(transaccion.getIdCuentaOrigen()),
                        transaccion.getMonto())
                .then(transaccionRepository.save(transaccion))
                .doOnSuccess(t -> log.info("Depósito procesado exitosamente"))
                .doOnError(e -> log.error("Error procesando depósito", e));
    }

    @Override
    public Mono<Transaccion> procesarRetiro(Transaccion transaccion) {
        log.info("Procesando retiro para la cuenta: {}", transaccion.getIdCuentaOrigen());
        transaccion.setTipo(TipoTransaccion.RETIRO);
        transaccion.setFecha(LocalDateTime.now());

        return cuentaService.retirarDeCuenta(
                        Integer.valueOf(transaccion.getIdCuentaOrigen()),
                        transaccion.getMonto())
                .then(transaccionRepository.save(transaccion))
                .doOnSuccess(t -> log.info("Retiro procesado exitosamente"))
                .doOnError(e -> log.error("Error procesando retiro", e));
    }

    @Override
    public Mono<Transaccion> procesarTransferencia(Transaccion transaccion) {
        log.info("Procesando transferencia desde cuenta {} hacia cuenta {}",
                transaccion.getIdCuentaOrigen(), transaccion.getIdCuentaDestino());
        transaccion.setTipo(TipoTransaccion.TRANSFERENCIA);
        transaccion.setFecha(LocalDateTime.now());

        return cuentaService.retirarDeCuenta(
                        Integer.valueOf(transaccion.getIdCuentaOrigen()),
                        transaccion.getMonto())
                .then(cuentaService.depositarEnCuenta(
                        Integer.valueOf(transaccion.getIdCuentaDestino()),
                        transaccion.getMonto()))
                .then(transaccionRepository.save(transaccion))
                .doOnSuccess(t -> log.info("Transferencia procesada exitosamente"))
                .doOnError(e -> log.error("Error procesando transferencia", e));
    }

    @Override
    public Flux<Transaccion> obtenerHistorialTransacciones(Integer idCuenta) {
        log.info("Obteniendo historial de transacciones para la cuenta: {}", idCuenta);
        return transaccionRepository.findByIdCuentaOrigen(idCuenta.toString())
                .doOnComplete(() -> log.info("Historial de transacciones obtenido exitosamente"))
                .doOnError(e -> log.error("Error obteniendo historial de transacciones", e));
    }
}