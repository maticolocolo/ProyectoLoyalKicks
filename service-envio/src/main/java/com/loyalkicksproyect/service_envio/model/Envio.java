package com.loyalkicksproyect.service_envio.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Envio
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long pedidoId;

    private Long sucursalOrigenId;

    @NotNull
    private String direccionDestino;

    private String estado;

    private String numeroSeguimiento;

    private LocalDate fechaDespacho;

    private LocalDate fechaEntregaEstimada;

    @Transient
    private Object datosPedido;

    @Transient
    private Object datosSucursal;
}
