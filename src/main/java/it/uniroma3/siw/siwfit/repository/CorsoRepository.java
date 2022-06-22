package it.uniroma3.siw.siwfit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.siwfit.model.Categoria;
import it.uniroma3.siw.siwfit.model.Corso;
import it.uniroma3.siw.siwfit.model.Trainer;

@Repository
public interface CorsoRepository extends CrudRepository<Corso, Long> {


	public boolean existsByNomeAndDataAndDifficoltaAndDurataAndDescrizioneAndSalaAndNumeroMaxPersoneAndTrainerAndCategoriaAndImg(
			String nome, String data, String difficolta, String durata, String descrizione, String sala,
			int numeroMaxPersone, Trainer trainer, Categoria categoria, String img);

	

}
