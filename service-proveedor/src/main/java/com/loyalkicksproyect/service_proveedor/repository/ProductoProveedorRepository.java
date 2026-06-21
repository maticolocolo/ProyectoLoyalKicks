package com.loyalkicksproyect.service_proveedor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loyalkicksproyect.service_proveedor.model.ProductoProveedor;

public interface ProductoProveedorRepository extends JpaRepository<ProductoProveedor, Long>
{
    List<ProductoProveedor> findByProductoId(Long productoId);
}
