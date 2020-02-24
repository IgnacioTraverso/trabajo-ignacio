package example.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import example.model.EquipoModel;

@Component
public class EquipoValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clase) {
		return EquipoModel.class.isAssignableFrom(clase);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		EquipoModel equipo=(EquipoModel)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeq", "required.nomeq","El nombre es obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "director", "required.director","El nombre del director es obrigatorio");
		
		if(equipo.getNomeq().length()>20) {
			errors.rejectValue("nomeq", "nomeq.incorrect", "El nombreno puede ser tan grande");
		}
		
		if(equipo.getDirector().length()>50) {
			errors.rejectValue("director", "director.incorrect", "El nombre del director no puede ser tan grande");
		}
		
		
	}


}