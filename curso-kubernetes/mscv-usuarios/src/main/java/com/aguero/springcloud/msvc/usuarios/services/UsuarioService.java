package com.aguero.springcloud.msvc.usuarios.services;

import com.aguero.springcloud.msvc.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario save(Usuario usuario);
    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    void delete(Long id);

    List<Usuario> listarPorids(List<Long> ids);
}
