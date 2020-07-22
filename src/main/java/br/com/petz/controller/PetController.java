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

import br.com.petz.models.Pet;
import br.com.petz.service.PetService;

@RestController
@RequestMapping("/petz/pet")
public class PetController {
	
	private static Logger log = LoggerFactory.getLogger(PetController.class);

	@Autowired
	private PetService service;

	@GetMapping("/pets")
	public ResponseEntity<List<Pet>> getAllPet() {
		log.info("Localizating all pets");
		List<Pet> pet = new ArrayList<>();
		try {
			pet = service.getAllPet();
		} catch (Exception e) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pet);
	}

	@GetMapping("/pet/{id}")
	public ResponseEntity<Pet> getPetById(@PathVariable("id") Integer id) {
		
		log.info("Try to find pet by id: " + id);
		return service.getPetById(id).map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.notFound().build());

	}

	@PostMapping("/cadastro")
	public ResponseEntity<Pet> createPet(@RequestBody Pet body) {
		log.info("Receiving request to save a entity: " + body);
		Pet pet = null;
		try {
			pet = service.createPet(body);
		} catch (Exception e) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pet);
	}

	@PutMapping("/editar")
	public ResponseEntity<Pet> editPet(@RequestBody Pet body) {
		log.info("Receiving request to edit a entity: " + body);

		Pet pet = null;
		try {
			pet = service.editPet(body);
		} catch (Exception e) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pet);
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable("id") Integer id) {
		log.info("Receiving request to delete a entity: " + id);

		boolean deleted = false;
		try {
			deleted = service.deletePet(id);
		} catch (Exception e) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(deleted == true ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
	}

}
