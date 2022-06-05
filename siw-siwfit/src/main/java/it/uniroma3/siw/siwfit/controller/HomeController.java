package it.uniroma3.siw.siwfit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.siwfit.controller.validator.UserValidator;
import it.uniroma3.siw.siwfit.model.User;
import it.uniroma3.siw.siwfit.service.CorsoService;
import it.uniroma3.siw.siwfit.service.CredenzialiService;
import it.uniroma3.siw.siwfit.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private CorsoService corsoService;
	
	@Autowired
	private CredenzialiService credenzialiService;

	@GetMapping("/user/corsi_prenotati")
	public String getCorsiPrenotati(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	User user = credenzialiService.getCredenziali(userDetails.getUsername()).getUser();
		model.addAttribute("user", user);
		return "/user/corsi_prenotati.html";
	}

	@GetMapping("/user/homeuser")
	public String getHomeUser(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	User user = credenzialiService.getCredenziali(userDetails.getUsername()).getUser();
		model.addAttribute("user",user);
		return "user/homeUser.html";
	}
	
	@GetMapping("/admin/homeadmin")
	public String getHomeAdmin(Model model) {
		return "admin/homeAdmin.html";
	}

}