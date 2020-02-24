package example.configuration;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("dozer")
public class configuration {
	
	@Bean
	public DozerBeanMapper mapped() {
		return new DozerBeanMapper();
	}
}
