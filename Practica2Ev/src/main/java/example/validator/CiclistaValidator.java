package example.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import example.model.CiclistaModel;

public class CiclistaValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clase) {
		return CiclistaModel.class.isAssignableFrom(clase);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CiclistaModel ciclista=(CiclistaModel)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "required.nombre","El nombre es obligatorio");
		
		if(ciclista.getNombre().length()>100) 
		{
			errors.rejectValue("nombre", "nombre.incorrect", "El nombre no puede ser tan grande");
		}

		if(ciclista.getDorsal()<=0) 
		{
			errors.rejectValue("dorsal", "dorsal.incorrect", "El dorsal tiene que ser mayor a 0");
		}
		
		if(ciclista.getEdad()<16) 
		{
			errors.rejectValue("edad", "edad.incorrect", "La edad debe ser mayor de 16");
		}
		
		
	}


}
