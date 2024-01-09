package com.jaguero.springcloud.msvc.cursos.repositories;

import com.jaguero.springcloud.msvc.cursos.models.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso,Long> {

    @Modifying//estás indicando a Spring Data JPA que esta consulta modificará el estado de la base de datos
    @Query("delete from CursoUsuario cu where cu.usuarioId=?1")
    void eliminarCursoUsuario(Long id);
}
