package com.jaguero.springcloud.msvc.cursos.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="cursos_usuarios")
@Data
public class CursoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id",unique = true)
    private Long usuarioId;

}
