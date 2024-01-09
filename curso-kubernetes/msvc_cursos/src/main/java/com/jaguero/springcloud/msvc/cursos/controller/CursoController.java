package com.jaguero.springcloud.msvc.cursos.controller;

import com.jaguero.springcloud.msvc.cursos.models.Usuario;
import com.jaguero.springcloud.msvc.cursos.models.entities.Curso;
import com.jaguero.springcloud.msvc.cursos.services.CursoServiceImpl;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CursoController {

    @Autowired
    private CursoServiceImpl service;

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Curso> o = service.porIdConUsuarios(id);
        if (o.isPresent()) {
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Curso curso) {
        return ResponseEntity.status(201).body(service.save(curso));
    }

    @PutMapping("/asignar-usuario/{cursoId}")
    public ResponseEntity<?> asignarUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId) {
        Optional<Usuario> o;
        try {
            o = service.asignarUsuario(usuario, cursoId);

        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no exite el usuario");
        }
        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-usuario/{id}")
    @Transactional
    public ResponseEntity<?> eliminarCursoUsuario(@PathVariable Long id){
        service.eliminarCursoUsuario(id);
        return ResponseEntity.noContent().build();
    }


}
