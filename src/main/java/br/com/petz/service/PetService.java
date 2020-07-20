package br.com.petz.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petz.controller.PetzCrudController;
import br.com.petz.models.Cliente;
import br.com.petz.repository.PetRepository;

@Service
public class PetService {
	@Autowired
	private PetRepository repository;
	
	public static Logger log = LoggerFactory.getLogger(PetzCrudController.class);
	
	
	public List<Cliente> getAllClients(){
		return repository.findAll();
	}
	
	
	public Optional<Cliente> getClientById(Integer id){
		return repository.findById(id);
	}
	
	
	public Cliente createClient( Cliente cliente){
		log.info("Saving entity" + cliente);
		return repository.saveAndFlush(cliente);
	}
	
	
	public Cliente editClient(Cliente cliente){
		if(repository.findById(cliente.getId()).isPresent()) {
			return repository.saveAndFlush(cliente);
		}
		return null;
	}
	
	
	public boolean  deleteClient(Integer id){
		if(repository.findById(id).isPresent()) {
			 repository.deleteById(id);
			 return true;
		}
		return false;
		
	}


}
