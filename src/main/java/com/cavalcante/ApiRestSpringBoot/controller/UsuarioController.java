package com.cavalcante.ApiRestSpringBoot.controller;

import com.cavalcante.ApiRestSpringBoot.model.UsuarioModel;
import com.cavalcante.ApiRestSpringBoot.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/usuario"})
public class UsuarioController {
    private UsuarioRepository repository;
    UsuarioController(UsuarioRepository usuarioRepository){
        this.repository = usuarioRepository;
    }
    @GetMapping
    public List findAll(){
        return (List) repository.findAll();
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public UsuarioModel create(@RequestBody UsuarioModel usuarioModel){
        return repository.save(usuarioModel);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id")Integer id, @RequestBody UsuarioModel usuarioModel){
        return repository.findById(id)
                .map(record-> {
                    record.setNome(usuarioModel.getNome());
                    record.setLogin(usuarioModel.getLogin());
                    record.setSenha(usuarioModel.getSenha());
                    UsuarioModel update = repository.save(record);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity <?> delete(@PathVariable Integer id){
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
