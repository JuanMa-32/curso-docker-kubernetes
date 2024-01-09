package com.aguero.springcloud.msvc.usuarios.services;

import com.aguero.springcloud.msvc.usuarios.clients.CursoClientRest;
import com.aguero.springcloud.msvc.usuarios.models.entity.Usuario;
import com.aguero.springcloud.msvc.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private CursoClientRest clientRest;
    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
        clientRest.eliminarCursoUsuario(id);
    }

    @Override
    public List<Usuario> listarPorids(List<Long> ids) {
        return repository.findAllById(ids);
    }


}
