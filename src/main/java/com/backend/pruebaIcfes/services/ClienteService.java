package com.backend.pruebaIcfes.services;

import java.util.Optional;
import com.backend.pruebaIcfes.models.entity.Cliente;

public interface ClienteService {
	public Iterable <Cliente> findAll();
	public Optional<Cliente> findById(Long id);
	public Cliente save(Cliente cliente);
	public void deleteById(Long id);
}
