package com.loyalkicksproyect.service_reporte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResumen
{
    private Long id;

    private Long usuarioId;

    private String estado;

    private Double total;
}
