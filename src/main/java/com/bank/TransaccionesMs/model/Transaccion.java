package com.bank.TransaccionesMs.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "transacciones")
public class Transaccion {
    @Id
    private String id;
    private TipoTransaccion tipo;
    private Double monto;
    private LocalDateTime fecha;
    private String idCuentaOrigen;
    private String idCuentaDestino;
    private String estado;
}
