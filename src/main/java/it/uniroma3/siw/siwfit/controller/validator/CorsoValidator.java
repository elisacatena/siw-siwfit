package it.uniroma3.siw.siwfit.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.siwfit.model.Corso;
import it.uniroma3.siw.siwfit.service.CorsoService;

@Component
public class CorsoValidator implements Validator{

	@Autowired
	private CorsoService corsoService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Corso.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(this.corsoService.alreadyExists((Corso)target)) {
			errors.reject("corso.duplicato");
		}
	}

	
}
