package com.aguero.springcloud.msvc.usuarios.controllers;

import com.aguero.springcloud.msvc.usuarios.models.entity.Usuario;
import com.aguero.springcloud.msvc.usuarios.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioServiceImpl service;

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/usuarios-curso")
    public ResponseEntity<?> alumnosPorCurso(@RequestParam List<Long> ids){
        return ResponseEntity.ok(service.listarPorids(ids));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Usuario> o = service.findById(id);
        if (o.isPresent()) {
            Usuario us = o.get();
            return ResponseEntity.ok(us);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Usuario us) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(us));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Usuario us, @PathVariable Long id) {
        Optional<Usuario> resp = service.findById(id);
        if (!resp.isEmpty()) {
            Usuario usuario = resp.get();
            usuario.setEdad(us.getEdad());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(usuario));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Usuario> o = service.findById(id);
        if(o.isPresent()){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
