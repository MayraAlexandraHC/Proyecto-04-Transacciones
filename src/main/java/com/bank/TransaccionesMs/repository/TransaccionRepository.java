package com.bank.TransaccionesMs.repository;

import com.bank.TransaccionesMs.model.Transaccion;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TransaccionRepository extends ReactiveMongoRepository<Transaccion, String> {
    Flux<Transaccion> findByIdCuentaOrigen(String idCuenta);
}