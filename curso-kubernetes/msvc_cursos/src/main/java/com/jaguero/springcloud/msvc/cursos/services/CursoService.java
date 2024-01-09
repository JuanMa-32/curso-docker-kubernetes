package com.jaguero.springcloud.msvc.cursos.services;

import com.jaguero.springcloud.msvc.cursos.models.Usuario;
import com.jaguero.springcloud.msvc.cursos.models.entities.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> findAll();
    Optional<Curso> findById(Long id);
    Optional<Curso> porIdConUsuarios(Long id);
    Curso save(Curso curso);

    void eliminarCursoUsuario(Long id);

    Optional<Usuario> asignarUsuario(Usuario usuario,Long idCurso);
    Optional<Usuario> crearUsuario(Usuario usuario,Long idCurso);
    Optional<Usuario> eliminarUsuario(Usuario usuario,Long idCurso);


}
