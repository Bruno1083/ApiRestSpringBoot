package com.cavalcante.ApiRestSpringBoot.controller;

import com.cavalcante.ApiRestSpringBoot.repository.UsuarioRepository;
import org.hibernate.mapping.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(path = "/usuario/{codigo}")
    public ResponseEntity findById(@PathVariable("codigo") Integer codigo){
        return repository.findById(codigo)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
}
