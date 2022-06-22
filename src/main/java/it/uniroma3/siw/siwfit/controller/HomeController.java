package it.uniroma3.siw.siwfit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.siwfit.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/user/corsi_prenotati/{id}")
	public String getCorsiPrenotati(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", this.userService.findById(id));
		return "user/corsi_prenotati.html";
	}

	@GetMapping("/user/homeuser/{id}")
	public String getHomeUser(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", this.userService.findById(id));
		return "user/homeUser.html";
	}
	
	@GetMapping("/admin/homeadmin")
	public String getHomeAdmin(Model model) {
		return "admin/homeAdmin.html";
	}

}