package com.backend.pruebaIcfes.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.pruebaIcfes.models.entity.Cliente;
import com.backend.pruebaIcfes.services.ClienteService;

@CrossOrigin(origins = { "*" })
@RequestMapping("/clientes")
@RestController
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(clienteService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> listarPorId(@PathVariable Long id) {
		Optional<Cliente> alumnoOptional = clienteService.findById(id);
		if (alumnoOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(alumnoOptional.get());
	}

	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Cliente cliente) {
		Cliente clienteCreado = clienteService.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
	}

	@PutMapping
	public ResponseEntity<?> actualizar(@RequestBody Cliente cliente) {
		Optional<Cliente> clienteOptional = clienteService.findById(cliente.getId());
		if (clienteOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Cliente clienteDB = clienteOptional.get();
		clienteDB.setNombre(cliente.getNombre());
		clienteDB.setApellido(cliente.getApellido());
		clienteDB.setEmail(cliente.getEmail());
		clienteDB.setEdad(cliente.getEdad());
		clienteDB.setDocumento(cliente.getDocumento());

		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteDB));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		clienteService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
