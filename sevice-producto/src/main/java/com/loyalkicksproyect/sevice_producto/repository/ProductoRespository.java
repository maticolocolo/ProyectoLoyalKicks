package com.loyalkicksproyect.sevice_producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loyalkicksproyect.sevice_producto.model.Producto;

public interface ProductoRespository extends JpaRepository<Producto,Long>
{
    
}
