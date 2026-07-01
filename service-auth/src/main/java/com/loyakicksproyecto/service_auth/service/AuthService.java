package com.loyakicksproyecto.service_auth.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loyakicksproyecto.service_auth.dto.AuthRequest;
import com.loyakicksproyecto.service_auth.model.Rol;
import com.loyakicksproyecto.service_auth.model.Usuario;
import com.loyakicksproyecto.service_auth.repository.RolRepository;
import com.loyakicksproyecto.service_auth.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Service
public class AuthService 
{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secreto;

    public String registrar(AuthRequest request)
    {
        if(usuarioRepository.findByNombreUsuario(request.getNombreUsuario()).isPresent())
        {
            throw new RuntimeException("El nombre de usuario ya existe.");
        }

        Usuario nuevo = new Usuario();
        nuevo.setNombreUsuario(request.getNombreUsuario());
        nuevo.setCorreo(request.getCorreo());
        nuevo.setContresenia(passwordEncoder.encode(request.getContrasenia()));

        Rol rolCliente = rolRepository.findByNombreRol("CLIENTE")
            .orElseThrow(() -> new RuntimeException("Rol CLIENTE no encontrado"));
        nuevo.getRoles().add(rolCliente);

        usuarioRepository.save(nuevo);
        return "Usuario registrado exitosamente";
    }

    @Transactional(readOnly = true)
    public String login(String nombreUsuario, String contresenia)
    {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas."));
        

        if(!passwordEncoder.matches(contresenia, usuario.getContresenia()))
        {
            throw new RuntimeException("Credenciales invalidas");
        }

        List<String> roles = usuario.getRoles().stream()
            .map(Rol::getNombreRol)
            .collect(Collectors.toList());

        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + 86400000);

        return Jwts.builder()
            .setSubject(usuario.getNombreUsuario())
            .claim("roles", roles)
            .setIssuedAt(ahora)
            .setExpiration(expiracion)
            .signWith(
                Keys.hmacShaKeyFor(secreto.getBytes(StandardCharsets.UTF_8)),
                SignatureAlgorithm.HS256
            )
            .compact();
    }

}
