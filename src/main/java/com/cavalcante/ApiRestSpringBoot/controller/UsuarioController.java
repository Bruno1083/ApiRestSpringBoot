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
    @GetMapping(path = "/usuario/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public UsuarioModel create(@RequestBody UsuarioModel usuarioModel){
        return repository.save(usuarioModel);
    }
}
