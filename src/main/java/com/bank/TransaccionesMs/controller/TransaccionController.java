package com.bank.TransaccionesMs.controller;

import com.bank.TransaccionesMs.model.Transaccion;
import com.bank.TransaccionesMs.service.TransaccionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/transacciones")
@RequiredArgsConstructor
public class TransaccionController {

    private final TransaccionService transaccionService;

    @PostMapping("/deposito")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Transaccion> crearDeposito(@RequestBody Transaccion transaccion) {
        log.info("Recibida solicitud de depósito para cuenta: {}", transaccion.getIdCuentaOrigen());
        return transaccionService.procesarDeposito(transaccion);
    }

    @PostMapping("/retiro")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Transaccion> crearRetiro(@RequestBody Transaccion transaccion) {
        log.info("Recibida solicitud de retiro para cuenta: {}", transaccion.getIdCuentaOrigen());
        return transaccionService.procesarRetiro(transaccion);
    }

    @PostMapping("/transferencia")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Transaccion> crearTransferencia(@RequestBody Transaccion transaccion) {
        log.info("Recibida solicitud de transferencia desde cuenta: {} hacia cuenta: {}",
                transaccion.getIdCuentaOrigen(), transaccion.getIdCuentaDestino());
        return transaccionService.procesarTransferencia(transaccion);
    }

    @GetMapping("/historial/{idCuenta}")
    public Flux<Transaccion> obtenerHistorialTransacciones(@PathVariable Integer idCuenta) {
        log.info("Recibida solicitud de historial de transacciones para cuenta: {}", idCuenta);
        return transaccionService.obtenerHistorialTransacciones(idCuenta);
    }
}