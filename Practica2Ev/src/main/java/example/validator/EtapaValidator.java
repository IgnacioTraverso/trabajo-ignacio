package example.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import example.model.EtapaModel;

@Component
public class EtapaValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clase) {
		return EtapaModel.class.isAssignableFrom(clase);
	}

	@Override
	public void validate(Object target, Errors errors) {
		EtapaModel etapa=(EtapaModel)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "salida", "required.salida","La salida es obligatoria");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "llegada", "required.llegada","La llegada es obligatoria");
		if(etapa.getKm()<=0) 
		{
			errors.rejectValue("km", "km.incorrect", "Los km deben ser mayores a 0");
		}
		if(etapa.getSalida().length()>50) 
		{
			errors.rejectValue("salida", "salida.incorrect", "La salida no puede ser tan grande");
		}
		if(etapa.getLlegada().length()>50) 
		{
			errors.rejectValue("llegada", "llegada.incorrect", "La llegada no puede ser tan grande");
		}
		
	}

}
