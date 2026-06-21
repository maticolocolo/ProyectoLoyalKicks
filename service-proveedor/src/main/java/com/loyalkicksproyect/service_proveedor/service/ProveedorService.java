package com.loyalkicksproyect.service_proveedor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.loyalkicksproyect.service_proveedor.model.ProductoProveedor;
import com.loyalkicksproyect.service_proveedor.model.Proveedor;
import com.loyalkicksproyect.service_proveedor.repository.ProductoProveedorRepository;
import com.loyalkicksproyect.service_proveedor.repository.ProveedorRepository;

@Service
public class ProveedorService
{
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProductoProveedorRepository productoProveedorRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Proveedor agregarProveedor(Proveedor proveedor)
    {
        return proveedorRepository.save(proveedor);
    }

    public List<Proveedor> listarTodo()
    {
        return proveedorRepository.findAll();
    }

    public Optional<Proveedor> buscarPorId(Long id)
    {
        return proveedorRepository.findById(id);
    }

    public Proveedor actualizarProveedor(Long id, Proveedor datos)
    {
        Proveedor p = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        p.setRazonSocial(datos.getRazonSocial());
        p.setRut(datos.getRut());
        p.setContactoNombre(datos.getContactoNombre());
        p.setContactoTelefono(datos.getContactoTelefono());
        p.setContactoCorreo(datos.getContactoCorreo());
        return proveedorRepository.save(p);
    }

    public void eliminarProveedor(Long id)
    {
        proveedorRepository.deleteById(id);
    }

    public ProductoProveedor asociarProducto(ProductoProveedor relacion)
    {
        if(relacion.getProveedor() != null && relacion.getProveedor().getId() != null)
        {
            Proveedor proveedor = proveedorRepository.findById(relacion.getProveedor().getId())
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
            relacion.setProveedor(proveedor);
        }
        return productoProveedorRepository.save(relacion);
    }

    public List<ProductoProveedor> listarPorProducto(Long productoId)
    {
        List<ProductoProveedor> relaciones = productoProveedorRepository.findByProductoId(productoId);
        relaciones.forEach(this::enriquecerConProducto);
        return relaciones;
    }

    private void enriquecerConProducto(ProductoProveedor relacion)
    {
        if(relacion.getProductoId() != null)
        {
            try
            {
                Object datosProducto = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8081/api/v1/productos/" + relacion.getProductoId())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();
                relacion.setDatosProducto(datosProducto);
            } catch (Exception e)
            {
                relacion.setDatosProducto("Producto no disponible");
            }
        }
    }
}
