package example.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.User;

import example.configuration.configuration;
import example.entity.UserRole;
import example.model.UserModel;
import example.repository.UserJpaRepository;
import example.repository.UserRoleJpaRepository;


@Service("UserServiceImpl")
public class UserServiceImpl implements UserDetailsService/*, UserService*/ {

	
	@Autowired
	@Qualifier("userRoleJpaRepository")
	private UserRoleJpaRepository userRoleJpaRepository;
	
	@Autowired
	@Qualifier("dozer")
	private configuration config;
	
	public example.entity.User model2entity(UserModel userModel) {
		return config.mapped().map(userModel, example.entity.User.class);
	}
	
	public UserModel entity2model(example.entity.User user) {
		return config.mapped().map(user, UserModel.class);
	}
	
	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userRepository;
	

	private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles){
		Set<GrantedAuthority> auths=new HashSet<GrantedAuthority>();
		
		for(UserRole userRole : userRoles) {
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<GrantedAuthority>(auths);
	}

	private User buildUser(example.entity.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(),user.getPassword(),user.isEnabled(),true,true,true,authorities);
	}
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		example.entity.User user=userRepository.findByUsername(name);
		List<GrantedAuthority> authorities =buildAuthorities(user.getUserRole());
		return buildUser(user,authorities);
	}
	
	
	public example.entity.User addUser(UserModel userModel) {
		example.entity.User user = model2entity(userModel);
		user.setEnabled(true);
		return userRepository.save(user);
	}
	
	
}
