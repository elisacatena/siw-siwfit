package it.uniroma3.siw.siwfit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.siwfit.controller.validator.CategoriaValidator;
import it.uniroma3.siw.siwfit.model.Categoria;
import it.uniroma3.siw.siwfit.model.Trainer;
import it.uniroma3.siw.siwfit.model.User;
import it.uniroma3.siw.siwfit.service.CategoriaService;
import it.uniroma3.siw.siwfit.service.CredenzialiService;

@Controller
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private CategoriaValidator categoriaValidator;
	
	@Autowired
	private CredenzialiService credenzialiService;
	
	@GetMapping("/user/categorie")
	public String getCategorieUser(Model model) {
		List<Categoria> categorie = categoriaService.findAll();
		model.addAttribute("categorie", categorie);
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	User user = credenzialiService.getCredenziali(userDetails.getUsername()).getUser();
		model.addAttribute("user",user);
		return "user/categorie.html";
	}
	
	@GetMapping("/admin/categorie")
	public String getCategorieAdmin(Model model) {
		List<Categoria> categorie = categoriaService.findAll();
		model.addAttribute("categorie", categorie);
		return "admin/categoria/categorie.html";
	}
	
	@GetMapping("/admin/crea_categoria")
	public String addCategoriaForm(Model model) {
		model.addAttribute("categoria", new Trainer());
		return "admin/categoria/crea_categoria.html";
	}
	
	@PostMapping("/admin/new_categoria") 
	public String addTrainer(@Valid @ModelAttribute("categoria") Categoria categoria, BindingResult bindingResult, Model model) {		
		this.categoriaValidator.validate(categoria, bindingResult);
		if(!bindingResult.hasErrors()) {     
			this.categoriaService.save(categoria);
			model.addAttribute("categoria", categoria);
			return "redirect:/admin/categorie";   
		}
		else {
			return "admin/categoria/crea_categoria.html";
		}
	}
	
	@GetMapping("/admin/modifica_categoria/{id}")
	public String modificaTrainerForm(@PathVariable("id")  Long id, Model model) {
		model.addAttribute("categoria", this.categoriaService.findById(id));
		return "admin/categoria/modifica_categoria.html";
	}
	
	@PostMapping("/admin/edit_categoria/{id}") 
	public String modificaTrainer(@PathVariable("id")  Long id, @Valid @ModelAttribute("categoria") Categoria categoria, BindingResult bindingResult, Model model) {		
//		this.trainerValidator.validate(trainer, bindingResult);
//		if (!bindingResult.hasErrors()){ // se i dati sono corretti
//			Trainer vecchioTrainer = this.trainerService.findById(id);
//			vecchioTrainer.setId(trainer.getId());
//			vecchioTrainer.setNome(trainer.getNome());
//			vecchioTrainer.setCognome(trainer.getCognome());
//			this.trainerService.save(vecchioTrainer);
//			model.addAttribute("trainer", vecchioTrainer);
//			return "redirect:/admin/trainers";
//			} 
//		else {
//			return "admin/trainer/modifica_trainer.html"; // ci sono errori, torna alla form iniziale
//		}
		Categoria c = categoria;
		this.categoriaService.deleteById(id);
		this.categoriaValidator.validate(c, bindingResult);
		if (!bindingResult.hasErrors()){ // se i dati sono corretti
			this.categoriaService.save(c);
			model.addAttribute("categoria", c);
			return "redirect:/admin/categorie";
		} 
		else {
			return "admin/categoria/modifica_categoria.html"; // ci sono errori, torna alla form iniziale
		}
	}
	
	@GetMapping("/admin/delete_categoria/{id}")
	public String deleteTrainer(@PathVariable Long id) {
		this.categoriaService.deleteById(id);
		return "redirect:/admin/categorie";
	}
	
	@GetMapping("/admin/dettagli_categoria/{id}")
	public String getDettagliCorso(@PathVariable Long id, Model model) {
		model.addAttribute("categoria", this.categoriaService.findById(id));
		return "/admin/categoria/dettagli_categoria";
	}


}
