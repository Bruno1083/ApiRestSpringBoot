package com.cavalcante.ApiRestSpringBoot.repository;

import com.cavalcante.ApiRestSpringBoot.model.UsuarioModel;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<UsuarioModel, Integer> {
}
