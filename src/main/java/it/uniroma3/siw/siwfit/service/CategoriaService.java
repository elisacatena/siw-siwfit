package it.uniroma3.siw.siwfit.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.siwfit.model.Categoria;
import it.uniroma3.siw.siwfit.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Transactional
	public void save(Categoria categoria) {
		categoriaRepository.save(categoria);
	}
	
	public Categoria findById(Long id) {
		return categoriaRepository.findById(id).get();
	}
	
	public List<Categoria> findAll(){
		List<Categoria> categorie = new ArrayList<Categoria>();
		for (Categoria c : categoriaRepository.findAll()) {
			categorie.add(c);
		}
		return categorie;
	}
	
	public boolean alreadyExists(Categoria categoria) {
        return this.categoriaRepository.existsByNome(categoria.getNome());
    }

	@Transactional
	public void deleteById(Long id) {
		this.categoriaRepository.deleteById(id);
	}
}
