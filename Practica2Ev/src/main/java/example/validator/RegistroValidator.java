package example.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import example.model.UserModel;

@Component
public class RegistroValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clase) {
		return UserModel.class.isAssignableFrom(clase);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserModel user=(UserModel)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required.username","El nombre es obligatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password","La contraseña es obligatorio");
		if(user.getUsername().length()>50) 
		{
			errors.rejectValue("username", "username.incorrect", "El nombre es demasiado grande");
		}
		if(user.getPassword().length()>30 || user.getPassword().length()<4) 
		{
			errors.rejectValue("password", "password.incorrect", "La contraseña tiene que ser mayor a 4 y menor que 30");
		}
		
	}
}
