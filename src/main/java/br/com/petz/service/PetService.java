package br.com.petz.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petz.controller.ClienteController;
import br.com.petz.models.Pet;
import br.com.petz.repository.PetsRepository;

@Service
public class PetService {

public static Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private PetsRepository repository;
	
	public List<Pet> getAllPet(){
		log.info("Finding all pets registereds");
		return repository.findAll();
	}
	
	
	public Optional<Pet> getPetById(Integer id){
		log.info("Find pet by id: " + id);
		return repository.findById(id);
	}
	
	
	public Pet createPet( Pet pet){
		log.info("Saving entity" + pet);
		return repository.saveAndFlush(pet);
	}
	
	
	public Pet editPet(Pet pet){
		if(repository.findById(pet.getId()).isPresent()) {
			log.info("Editing entity" + pet);
			return repository.saveAndFlush(pet);
		}
		return null;
	}
	
	
	public boolean  deletePet(Integer id){
		if(repository.findById(id).isPresent()) {
			log.info("Deleting entity id :" + id);
			 repository.deleteById(id);
			 return true;
		}
		return false;
		
	}

}
