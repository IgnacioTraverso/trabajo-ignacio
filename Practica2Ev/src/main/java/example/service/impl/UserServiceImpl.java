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
import example.service.UserService;


@Service("UserServiceImpl")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;
	
	@Autowired
	@Qualifier("userRoleJpaRepository")
	private UserRoleJpaRepository userRoleJpaRepository;
	
	@Autowired
	@Qualifier("dozer")
	private configuration config;
	
	@Override
	public example.entity.User model2entity(UserModel userModel) {
		return config.mapped().map(userModel, example.entity.User.class);
	}

	@Override
	public UserModel entity2model(example.entity.User user) {
		return config.mapped().map(user, UserModel.class);
	}

	@Override
	public List<UserModel> listAllUser() {
		List<UserModel> userModel=new ArrayList<UserModel>();
		for(example.entity.User user: userJpaRepository.findAll())
			userModel.add(entity2model(user));
		return userModel;
	}

	@Override
	public example.entity.User addUser(UserModel userModel) {
		example.entity.User user=model2entity(userModel);
		user.setEnabled(true);
		return userJpaRepository.save(user);
	}

	@Override
	public boolean checkUser(String username) {
		boolean checked=false;
		List<UserModel> listUser=listAllUser();
		for(UserModel userModel: listUser) {
			System.out.println(userModel.getUsername());
			if(userModel.getUsername().equals(username)) 
			{
				System.out.println("Son iguales");
				checked=true;
			}
		}
		return checked;
	}

	@Override
	public void save(UserModel userModel) {
		example.entity.User user = new example.entity.User();
		user.setUsername(userModel.getUsername());
		user.setPassword(userModel.getPassword());
		user.setEnabled(true);
		user.setUserRole(new HashSet<>(userRoleJpaRepository.findAll()));
		userJpaRepository.save(user);
		
		UserRole userRole = new UserRole();
		userRole.setRole(userModel.getRol());
		userRole.setUser(user);
		userRoleJpaRepository.save(userRole);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		example.entity.User user=userJpaRepository.findByUsername(username);
		List<GrantedAuthority> authorities=buildAuthorities(user.getUserRole());
		return buildUser(user,authorities);
	}

	private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRole){
		Set<GrantedAuthority> auths=new HashSet<GrantedAuthority>();
		for(UserRole role: userRole) {
			auths.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return new ArrayList<GrantedAuthority>(auths);
	}
	
	private User buildUser(example.entity.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true , true, authorities);
	}
	
}