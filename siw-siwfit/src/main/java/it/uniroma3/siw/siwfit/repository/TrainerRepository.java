package it.uniroma3.siw.siwfit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.siwfit.model.Trainer;

@Repository
public interface TrainerRepository extends CrudRepository<Trainer, Long> {

	public boolean existsByNomeAndCognome(String nome, String cognome);

}
