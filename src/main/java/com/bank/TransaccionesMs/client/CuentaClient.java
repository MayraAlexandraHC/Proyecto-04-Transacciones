package com.bank.TransaccionesMs.client;

import com.bank.TransaccionesMs.model.Cuenta;
import com.bank.TransaccionesMs.model.Transaccion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CuentaClient {
    private final WebClient cuentaWebClient;

    public Mono<Cuenta> obtenerCuenta(Integer idCuenta) {
        System.out.println("Llamando al servicio de cuentas para obtener cuenta: " + idCuenta);
        return cuentaWebClient.get()
                .uri("/cuentas/" + idCuenta)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Cuenta.class)
                .doOnSuccess(cuenta -> System.out.println("Cuenta obtenida exitosamente: " + idCuenta))
                .doOnError(e -> System.out.println("Error al obtener cuenta: " + idCuenta + ". Error: " + e.getMessage()));
    }

    public Mono<Cuenta> depositarEnCuenta(Integer idCuenta, Double monto) {
        System.out.println("Llamando al servicio de cuentas para depositar en cuenta: " + idCuenta);
        Transaccion transaccion = new Transaccion();
        transaccion.setMonto(monto);

        return cuentaWebClient.put()
                .uri("/cuentas/" + idCuenta + "/depositar")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(transaccion)
                .retrieve()
                .bodyToMono(Cuenta.class)
                .doOnSuccess(c -> System.out.println("Depósito realizado exitosamente en cuenta: " + idCuenta))
                .doOnError(e -> System.out.println("Error al realizar depósito en cuenta: " + idCuenta + ". Error: " + e.getMessage()));
    }

    public Mono<Cuenta> retirarDeCuenta(Integer idCuenta, Double monto) {
        System.out.println("Llamando al servicio de cuentas para retirar de cuenta: " + idCuenta);
        Transaccion transaccion = new Transaccion();
        transaccion.setMonto(monto);

        return cuentaWebClient.put()
                .uri("/cuentas/" + idCuenta + "/retirar")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(transaccion)
                .retrieve()
                .bodyToMono(Cuenta.class)
                .doOnSuccess(c -> System.out.println("Retiro realizado exitosamente de cuenta: " + idCuenta))
                .doOnError(e -> System.out.println("Error al realizar retiro de cuenta: " + idCuenta + ". Error: " + e.getMessage()));
    }
}