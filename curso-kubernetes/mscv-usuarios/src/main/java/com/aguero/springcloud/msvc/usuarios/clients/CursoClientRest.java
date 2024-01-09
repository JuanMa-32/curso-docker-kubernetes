package com.aguero.springcloud.msvc.usuarios.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msvc-cursos",url = "msvc-cursos:8002")
public interface CursoClientRest {
    @DeleteMapping("/eliminar-usuario/{id}")
     void eliminarCursoUsuario(@PathVariable Long id);
}
