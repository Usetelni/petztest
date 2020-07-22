package br.com.petz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petz.models.Pet;

@Repository
public interface PetsRepository extends JpaRepository<Pet, Integer> {

}
