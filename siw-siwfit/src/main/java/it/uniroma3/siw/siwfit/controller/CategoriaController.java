package it.uniroma3.siw.siwfit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.siwfit.controller.validator.CategoriaValidator;
import it.uniroma3.siw.siwfit.model.Categoria;
import it.uniroma3.siw.siwfit.service.CategoriaService;
import it.uniroma3.siw.siwfit.service.UserService;

@Controller
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private CategoriaValidator categoriaValidator;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/categorie/{id}")
	public String getCategorieUser(@PathVariable("id") Long id, Model model) {
		List<Categoria> categorie = categoriaService.findAll();
		model.addAttribute("categorie", categorie);
		model.addAttribute("user", this.userService.findById(id));
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
		model.addAttribute("categoria", new Categoria());
		return "admin/categoria/crea_categoria.html";
	}
	
	@PostMapping("/admin/new_categoria") 
	public String addCategoria(@Valid @ModelAttribute("categoria") Categoria categoria, BindingResult bindingResult, Model model) {		
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
	public String modificaCategoriaForm(@PathVariable("id")  Long id, Model model) {
		model.addAttribute("categoria", this.categoriaService.findById(id));
		return "admin/categoria/modifica_categoria.html";
	}
	
	@PostMapping("/admin/edit_categoria/{id}") 
	public String modificaCategoria(@PathVariable("id")  Long id, @Valid @ModelAttribute("categoria") Categoria categoria, BindingResult bindingResult, Model model) {		
		this.categoriaValidator.validate(categoria, bindingResult);
		if (!bindingResult.hasErrors()) { 
			this.categoriaService.save(categoria);
			model.addAttribute("categoria", categoria);
			return "redirect:/admin/categorie";
		} 
		else {
			return "admin/categoria/modifica_categoria.html";
		}
	}
	
	@GetMapping("/admin/delete_categoria/{id}")
	public String deleteCategoria(@PathVariable Long id) {
		this.categoriaService.deleteById(id);
		return "redirect:/admin/categorie";
	}
	
	@GetMapping("/admin/dettagli_categoria/{id}")
	public String getDettagliCategoria(@PathVariable Long id, Model model) {
		model.addAttribute("categoria", this.categoriaService.findById(id));
		model.addAttribute("categorie",this.categoriaService.findAll());
		return "admin/categoria/dettagli_categoria";
	}


}
