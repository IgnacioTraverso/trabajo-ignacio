package example.crypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestCrypt {
	
	public String generatepassword(String password) {
		BCryptPasswordEncoder pas=new BCryptPasswordEncoder();
		System.out.println(pas.encode("admin"));
		return pas.encode("admin");
	}
	
}
//$2a$10$JGAmG89OH.AORawoKEehcOWOSUgaHEX5iVcRPf6RuAVkvU7qsuG9K
