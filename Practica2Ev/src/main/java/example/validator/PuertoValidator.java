package example.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import example.model.PuertoModel;

@Component
public class PuertoValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clase) {
		return PuertoModel.class.isAssignableFrom(clase);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PuertoModel puerto=(PuertoModel)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nompuerto", "required.nompuerto","El nombre  es obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categoria", "required.categoria","La categoria es obligatoria");
		if(puerto.getNompuerto().length()>50) 
		{
			errors.rejectValue("nompuerto", "nompuerto.incorrect", "El nombre no puede ser tan grande");
		}
		if(puerto.getCategoria().length()!=1) 
		{
			errors.rejectValue("categoria", "categoria.incorrect", "La categoria es 1 caracter");
		}
		if(puerto.getAltura()<=0) 
		{
			errors.rejectValue("altura", "altura.incorrect", "La altura tiene que ser mayor a 0");
		}
		if(puerto.getPendiente()<=0) 
		{
			errors.rejectValue("pendiente", "pendiente.incorrect", "La pendiente tine que ser mayor que 0");
		}
		
		
	}

}
