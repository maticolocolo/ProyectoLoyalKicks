package com.loyalkicksproyect.service_devolucion.model;

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
public class Devolucion
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long pedidoId;

    @NotNull
    private Long productoId;

    @NotNull
    private String motivo;

    private String estado;

    private LocalDate fechaSolicitud;

    @Transient
    private Object datosPedido;

    @Transient
    private Object datosProducto;
}
