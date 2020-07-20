package br.com.petz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petz.models.Cliente;

public interface PetRepository extends JpaRepository<Cliente, Integer> {

}
