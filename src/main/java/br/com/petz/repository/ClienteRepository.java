package br.com.petz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petz.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
