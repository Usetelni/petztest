package br.com.petz.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.petz.models.Cliente;
import br.com.petz.service.ClienteService;

@RestController
@RequestMapping("/petz/cliente")
public class ClienteController {

	private static Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private ClienteService service;

	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> getAllClients() {
		log.info("Localizating all clients");
		List<Cliente> clientes = new ArrayList<>();
		try {
			clientes = service.getAllClients();
		} catch (Exception e) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clientes);
	}

	@GetMapping("/cliente/{id}")
	public ResponseEntity<Cliente> getClientById(@PathVariable("id") Integer id) {
		
		log.info("Try to find client by id: " + id);
		return service.getClientById(id).map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.notFound().build());

	}

	@PostMapping("/cadastro")
	public ResponseEntity<Cliente> createClient(@RequestBody Cliente body) {
		log.info("Receiving request to save a entity: " + body);
		Cliente cliente = null;
		try {
			cliente = service.createClient(body);
		} catch (Exception e) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}

	@PutMapping("/editar")
	public ResponseEntity<Cliente> editClient(@RequestBody Cliente body) {
		log.info("Receiving request to edit a entity: " + body);

		Cliente cliente = null;
		try {
			cliente = service.editClient(body);
		} catch (Exception e) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable("id") Integer id) {
		log.info("Receiving request to delete a entity: " + id);

		boolean deleted = false;
		try {
			deleted = service.deleteClient(id);
		} catch (Exception e) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(deleted == true ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
	}
}