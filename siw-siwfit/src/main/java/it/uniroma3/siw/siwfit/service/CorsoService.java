package it.uniroma3.siw.siwfit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.siwfit.model.Corso;
import it.uniroma3.siw.siwfit.repository.CorsoRepository;

@Service
public class CorsoService {

	@Autowired
	private CorsoRepository corsoRepository;

	public Corso findById(Long id) {
		return corsoRepository.findById(id).get();
	}

	@Transactional
	public void save(Corso c) {
		this.corsoRepository.save(c);
	}

	public List<Corso> findAll() {
		List<Corso> corsi = new ArrayList<Corso>();
		for(Corso c : this.corsoRepository.findAll()) {
			corsi.add(c);
		}
		return corsi;
	}

	@Transactional
	public void deleteById(Long id) {
		this.corsoRepository.deleteById(id);
	}

	public boolean alreadyExists(Corso corso) {
		return this.corsoRepository.existsByNomeAndDataAndDifficoltaAndDurataAndDescrizioneAndSalaAndNumeroMaxPersoneAndTrainerAndCategoria(corso.getNome(), corso.getData(), corso.getDifficolta(), corso.getDurata(), corso.getDescrizione(), corso.getSala(), corso.getNumeroMaxPersone(), corso.getTrainer(), corso.getCategoria());
	}
}
