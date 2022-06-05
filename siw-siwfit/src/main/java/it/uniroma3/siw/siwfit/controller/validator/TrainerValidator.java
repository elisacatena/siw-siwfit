package it.uniroma3.siw.siwfit.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.siwfit.model.Trainer;
import it.uniroma3.siw.siwfit.service.TrainerService;

@Component
public class TrainerValidator implements Validator{

	@Autowired
	private TrainerService trainerService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Trainer.class.equals(clazz);
	}

	@Override
	public void validate(Object trainer, Errors errors) {
		if(this.trainerService.alreadyExistsByNomeAndCognome((Trainer)trainer)) {
			errors.reject("trainer.duplicato");
		}
	}

}
