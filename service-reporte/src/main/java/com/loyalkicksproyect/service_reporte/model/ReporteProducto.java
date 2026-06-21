package com.loyalkicksproyect.service_reporte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteProducto
{
    private Long productoId;

    private Object datosProducto;

    private Object datosInventario;
}
