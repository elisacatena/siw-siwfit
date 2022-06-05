package it.uniroma3.siw.siwfit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.siwfit.model.Corso;

@Repository
public interface CorsoRepository extends CrudRepository<Corso, Long> {

	public boolean existsByNome(String nome);

}
