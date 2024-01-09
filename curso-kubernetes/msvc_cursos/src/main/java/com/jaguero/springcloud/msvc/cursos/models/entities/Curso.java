package com.jaguero.springcloud.msvc.cursos.models.entities;

import com.jaguero.springcloud.msvc.cursos.models.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "cursos")
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)//orphanRemoval se utiliza para especificar si las entidades "hijas" (en este caso, CursoUsuario) deben ser eliminadas cuando se eliminan de la colección asociada
    @JoinColumn(name = "curso_id")//para especificar la columna de la base de datos que se utilizará para la relación entre entidades
    private List<CursoUsuario> cursoUsuarios;

    @Transient//se utiliza para indicar que un campo no debe ser persistido en la base de datos.
    private List<Usuario> usuarios;

    public void addCursoUsuario(CursoUsuario cursoUsuario){
        cursoUsuarios.add(cursoUsuario);
    }

}
