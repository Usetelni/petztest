package br.com.petz.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petz.controller.ClienteController;
import br.com.petz.models.Cliente;
import br.com.petz.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	
	public static Logger log = LoggerFactory.getLogger(ClienteController.class);
	
	
	public List<Cliente> getAllClients(){
		log.info("Find all clients registereds");
		return repository.findAll();
	}
	
	
	public Optional<Cliente> getClientById(Integer id){
		log.info("Find cliente by id: " + id);
		return repository.findById(id);
	}
	
	
	public Cliente createClient( Cliente cliente){
		log.info("Saving entity" + cliente);
		return repository.saveAndFlush(cliente);
	}
	
	
	public Cliente editClient(Cliente cliente){
		if(repository.findById(cliente.getId()).isPresent()) {
			log.info("Try to edit cliente  " + cliente);

			return repository.saveAndFlush(cliente);
		}
		return null;
	}
	
	
	public boolean  deleteClient(Integer id){
		if(repository.findById(id).isPresent()) {
			log.info("Try to delete cliente by id " + id);

			 repository.deleteById(id);
			 return true;
		}
		return false;
		
	}


}
