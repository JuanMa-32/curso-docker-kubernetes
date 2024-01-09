package com.jaguero.springcloud.msvc.cursos.services;

import com.jaguero.springcloud.msvc.cursos.clients.UsuarioClienteRest;
import com.jaguero.springcloud.msvc.cursos.models.Usuario;
import com.jaguero.springcloud.msvc.cursos.models.entities.Curso;
import com.jaguero.springcloud.msvc.cursos.models.entities.CursoUsuario;
import com.jaguero.springcloud.msvc.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private UsuarioClienteRest clienteRest;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Curso> porIdConUsuarios(Long id) {
        Optional<Curso> o = repository.findById(id);
        if(o.isPresent()){
            Curso curso = o.get();
            if(!curso.getCursoUsuarios().isEmpty()){
                List<Long> ids = curso.getCursoUsuarios().stream().map(co -> co.getUsuarioId())
                        .collect(Collectors.toList());
                List<Usuario> usuarios = clienteRest.alumnosPorCurso(ids);
                curso.setUsuarios(usuarios);
                return Optional.of(curso);
            }

        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        return repository.save(curso);
    }

    @Override
    public void eliminarCursoUsuario(Long id) {
        repository.eliminarCursoUsuario(id);
    }

    @Override
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long idCurso) {
      Optional<Curso> o = repository.findById(idCurso);
      if(o.isPresent()){
          //traigo el usuario usando el client
          Usuario usMsvc = clienteRest.findById(usuario.getId());


          Curso curso = o.get();

          CursoUsuario cursoUsuario = new CursoUsuario();
          cursoUsuario.setUsuarioId(usMsvc.getId());
          curso.addCursoUsuario(cursoUsuario);

          repository.save(curso);
          return Optional.of(usMsvc);
      }
      return Optional.empty();
    }

    @Override
    public Optional<Usuario> crearUsuario(Usuario usuario, Long idCurso) {
        Optional<Curso> o = repository.findById(idCurso);
        if(o.isPresent()){
            //traigo el usuario usando el client
            Usuario usMsvc = clienteRest.crear(usuario);


            Curso curso = o.get();

            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usMsvc.getId());
            curso.addCursoUsuario(cursoUsuario);

            repository.save(curso);
            return Optional.of(usMsvc);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long idCurso) {
        return Optional.empty();
    }
}
