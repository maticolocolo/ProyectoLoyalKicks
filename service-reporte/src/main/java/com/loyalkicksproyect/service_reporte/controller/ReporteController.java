package com.loyalkicksproyect.service_reporte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loyalkicksproyect.service_reporte.model.ReporteProducto;
import com.loyalkicksproyect.service_reporte.model.ReporteVentas;
import com.loyalkicksproyect.service_reporte.service.ReporteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/reportes")
@Tag(name = "Reportes", description = "Reportes agregados de ventas e inventario de Loyal Kicks")
public class ReporteController
{
    @Autowired
    private ReporteService reporteService;

    @Operation(summary = "Generar el reporte general de ventas")
    @GetMapping("/ventas")
    public ReporteVentas reporteVentas()
    {
        return reporteService.generarReporteVentas();
    }

    @Operation(summary = "Generar el reporte de un producto, combina catalogo e inventario")
    @GetMapping("/productos/{productoId}")
    public ReporteProducto reporteProducto(@PathVariable Long productoId)
    {
        return reporteService.generarReporteProducto(productoId);
    }
}
