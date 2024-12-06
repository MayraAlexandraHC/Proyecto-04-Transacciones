package com.bank.TransaccionesMs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {
    private Integer id;
    private String numeroCuenta;
    private Double saldo;
    private TipoCuenta tipoCuenta;
    private Integer clienteId;
}

