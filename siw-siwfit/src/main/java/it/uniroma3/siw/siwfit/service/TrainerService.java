package it.uniroma3.siw.siwfit.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.siwfit.model.Trainer;
import it.uniroma3.siw.siwfit.repository.TrainerRepository;

@Service
public class TrainerService {

	@Autowired
	private TrainerRepository trainerRepository;

	public boolean alreadyExists(Trainer trainer) {
		return this.trainerRepository.existsById(trainer.getId());
	}

	public List<Trainer> findAll() {
		List<Trainer> trainers = new ArrayList<Trainer>();
		for (Trainer trainer : trainerRepository.findAll()) {
			trainers.add(trainer);
		}
		return trainers;
	}

	public Trainer findById(Long id) {
		return this.trainerRepository.findById(id).get();
	}

	@Transactional
	public void save(@Valid Trainer trainer) {
		this.trainerRepository.save(trainer);
	}
	
	public boolean alreadyExistsByNomeAndCognome(Trainer trainer) {
		return this.trainerRepository.existsByNomeAndCognome(trainer.getNome(), trainer.getCognome());
	}

	@Transactional
	public void deleteById(Long id) {
		this.trainerRepository.deleteById(id);
	}
	
}
