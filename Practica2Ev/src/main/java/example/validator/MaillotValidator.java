package example.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import example.model.MaillotModel;

@Component
public class MaillotValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clase) {
		return MaillotModel.class.isAssignableFrom(clase);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MaillotModel maillot=(MaillotModel)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigo", "required.codigo","El codigo es obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipo", "required.tipo","El tipo es obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "color", "required.color","El color es obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "premio", "required.premio","El premio es obligatorio");
		
		if(maillot.getCodigo().length()>3) 
		{
			errors.rejectValue("codigo", "codigo.incorrect", "El codigo no puede ser tan grande");
		}
		if(maillot.getTipo().length()>20) 
		{
			errors.rejectValue("tipo", "tipo.incorrect", "El tipo no puede ser tan grande");
		}
		if(maillot.getColor().length()>20) 
		{
			errors.rejectValue("color", "color.incorrect", "El color no puede ser tan grande");
		}
		if(maillot.getPremio()<=0) 
		{
			errors.rejectValue("premio", "premio.incorrect", "El premio tiene que ser más que 0");
		}
	}
}
