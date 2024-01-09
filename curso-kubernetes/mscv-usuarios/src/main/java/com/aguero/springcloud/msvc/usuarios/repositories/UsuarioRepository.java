package com.aguero.springcloud.msvc.usuarios.repositories;

import com.aguero.springcloud.msvc.usuarios.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
