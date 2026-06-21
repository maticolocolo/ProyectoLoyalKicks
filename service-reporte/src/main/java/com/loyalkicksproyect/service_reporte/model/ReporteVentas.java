package com.loyalkicksproyect.service_reporte.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteVentas
{
    private Integer totalPedidos;

    private Double totalVendido;

    private Double ticketPromedio;

    private List<PedidoResumen> pedidos;
}
