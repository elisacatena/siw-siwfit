package it.uniroma3.siw.siwfit.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.siwfit.controller.validator.TrainerValidator;
import it.uniroma3.siw.siwfit.model.Trainer;
import it.uniroma3.siw.siwfit.service.TrainerService;
import it.uniroma3.siw.siwfit.service.UserService;

@Controller
public class TrainerController {
	
	@Autowired
	private TrainerService trainerService;
	
	@Autowired
	private TrainerValidator trainerValidator;
	
	@Autowired
	private  UserService userService;

	@GetMapping("/user/trainers/{id}")
	public String getAllTrainersUser(@PathVariable("id") Long id, Model model) {
		model.addAttribute("trainers", this.trainerService.findAll());	
    	model.addAttribute("user", this.userService.findById(id));
		return "user/trainers.html";
	}
	
	@GetMapping("/user/trainer/{id}/{idU}")
	public String getTrainer(@PathVariable("id") Long id, @PathVariable("idU") Long idU, Model model) {
		model.addAttribute("trainer", this.trainerService.findById(id));	
		model.addAttribute("user", this.userService.findById(idU));
		return "user/trainer.html";
	}
	
	@GetMapping("/admin/trainers")
	public String getAllTrainersAdmin(Model model) {
		model.addAttribute("trainers", this.trainerService.findAll());	
		return "admin/trainer/trainers.html";
	}
	
	@GetMapping("/admin/crea_trainer")
	public String addTrainerForm(Model model) {
		model.addAttribute("trainer", new Trainer());
		return "admin/trainer/crea_trainer.html";
	}
	
	@PostMapping("/admin/new_trainer") 
	public String addTrainer(@Valid @ModelAttribute("trainer") Trainer trainer, BindingResult bindingResult, Model model) {		
		this.trainerValidator.validate(trainer, bindingResult);
		if(!bindingResult.hasErrors()) {     
			this.trainerService.save(trainer);
			model.addAttribute("trainer", trainer);
			return "redirect:/admin/trainers";   
		}
		else {
			return "admin/trainer/crea_trainer.html";
		}
	}
	
	@GetMapping("/admin/modifica_trainer/{id}")
	public String modificaTrainerForm(@PathVariable("id")  Long id, Model model) {
		model.addAttribute("trainer", this.trainerService.findById(id));
		return "admin/trainer/modifica_trainer.html";
	}
	
	@PostMapping("/admin/edit_trainer/{id}") 
	public String modificaTrainer(@PathVariable("id")  Long id, @Valid @ModelAttribute("trainer") Trainer trainer, BindingResult bindingResult, Model model) {		
		this.trainerValidator.validate(trainer, bindingResult);
		if (!bindingResult.hasErrors()) { 
			this.trainerService.save(trainer);
			model.addAttribute("trainer", trainer);
			return "redirect:/admin/trainers";
		} 
		else {
			return "admin/trainer/modifica_trainer.html"; 
		}
	}
	
	@GetMapping("/admin/delete_trainer/{id}")
	public String deleteTrainer(@PathVariable Long id) {
		this.trainerService.deleteById(id);
		return "redirect:/admin/trainers";
	}
	
	@GetMapping("/admin/dettagli_trainer/{id}")
	public String getDettagliTrainer(@PathVariable Long id, Model model) {
		model.addAttribute("trainer", this.trainerService.findById(id));
		model.addAttribute("trainers", this.trainerService.findAll());
		return "admin/trainer/dettagli_trainer";
	}
	
}
