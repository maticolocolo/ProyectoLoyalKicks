package com.loyalkicksproyecto.service_pedido.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long usuarioId; //Lo dejo aqui para cuando tengamos el servicio de usuario

    @NotNull
    private LocalDateTime fecha;

    private String estado; // estados posible pendiente, confirmado, cancelado

    private Double total;
}
