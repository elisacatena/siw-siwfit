package it.uniroma3.siw.siwfit.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.siwfit.model.Categoria;
import it.uniroma3.siw.siwfit.service.CategoriaService;

@Component
public class CategoriaValidator implements Validator {

	@Autowired
	private CategoriaService categoriaService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Categoria.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(this.categoriaService.alreadyExists((Categoria)target)) {
			errors.reject("categoria.duplicato");
		}
	}

}
