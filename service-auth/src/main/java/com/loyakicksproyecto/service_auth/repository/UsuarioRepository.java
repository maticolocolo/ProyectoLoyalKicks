package com.loyakicksproyecto.service_auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loyakicksproyecto.service_auth.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
