package com.jaguero.springcloud.msvc.cursos.clients;

import com.jaguero.springcloud.msvc.cursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-usuarios", url = "msvc-usuarios:8001")
public interface UsuarioClienteRest {

    @GetMapping("/{id}")
    Usuario findById(@PathVariable Long id);

    @PostMapping
    Usuario crear(@RequestBody Usuario us);

    @GetMapping("/usuarios-curso")
    List<Usuario> alumnosPorCurso(@RequestParam Iterable<Long> ids);

}
