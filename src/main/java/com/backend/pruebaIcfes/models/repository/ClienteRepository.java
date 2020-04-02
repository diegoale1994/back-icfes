package com.backend.pruebaIcfes.models.repository;

import org.springframework.data.repository.CrudRepository;
import com.backend.pruebaIcfes.models.entity.Cliente;

public interface ClienteRepository  extends CrudRepository<Cliente, Long> {

}
