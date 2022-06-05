package it.uniroma3.siw.siwfit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.siwfit.controller.validator.CategoriaValidator;
import it.uniroma3.siw.siwfit.model.Categoria;
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
		return "admin/categorie.html";
	}


}
