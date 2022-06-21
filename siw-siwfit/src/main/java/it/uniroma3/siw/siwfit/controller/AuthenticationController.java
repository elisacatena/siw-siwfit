package it.uniroma3.siw.siwfit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.siwfit.controller.validator.CredenzialiValidator;
import it.uniroma3.siw.siwfit.controller.validator.UserValidator;
import it.uniroma3.siw.siwfit.model.Credenziali;
import it.uniroma3.siw.siwfit.model.User;
import it.uniroma3.siw.siwfit.service.CredenzialiService;
import it.uniroma3.siw.siwfit.service.UserService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private CredenzialiService credenzialiService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private CredenzialiValidator credenzialiValidator;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String showLoginForm (Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET) 
	public String logout(Model model) {
		return "index";
	}
	
    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
        
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credenziali credenziali = credenzialiService.getCredenziali(userDetails.getUsername());
    	if (credenziali.getRole().equals(Credenziali.DEFAULT_ROLE)) {
        	User user = credenzialiService.getCredenziali(userDetails.getUsername()).getUser();
    		model.addAttribute("user", user);
            return "user/homeUser";
        }
    	if (credenziali.getRole().equals(Credenziali.ADMIN_ROLE)) {
            return "admin/homeAdmin";
        }
        return "index";
    }
	
    @RequestMapping(value = "/register", method = RequestMethod.GET) 
	public String showRegisterForm (Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credenziali", new Credenziali());
		return "registerForm";
	}
    
    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user,
                 BindingResult userBindingResult,
                 @ModelAttribute("credenziali") Credenziali credenziali,
                 BindingResult credenzialiBindingResult,
                 Model model) {

        // validate user and credentials fields
        this.userValidator.validate(user, userBindingResult);
        this.credenzialiValidator.validate(credenziali, credenzialiBindingResult);

        // if neither of them had invalid contents, store the User and the Credentials into the DB
        if(!userBindingResult.hasErrors() && ! credenzialiBindingResult.hasErrors()) {
            // set the user and store the credentials;
            // this also stores the User, thanks to Cascade.ALL policy
            credenziali.setUser(user);
            credenzialiService.saveCredenziali(credenziali);
            userService.save(user);
            return "index";
        }
        return "registerForm";
    }
}
