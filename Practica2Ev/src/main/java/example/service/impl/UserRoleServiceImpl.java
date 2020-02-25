package example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import example.configuration.configuration;
import example.entity.UserRole;
import example.model.UserRoleModel;
import example.repository.UserRoleJpaRepository;
import example.service.UserRoleService;

@Service("userRoleServiceImpl")
public class UserRoleServiceImpl implements UserRoleService{

	
	@Autowired
	@Qualifier("dozer")
	private configuration config;
	
	@Override
	public UserRole model2entity(UserRoleModel userRoleModel) {
		return config.mapped().map(userRoleModel, UserRole.class);

	}

	@Override
	public UserRoleModel entity2model(UserRole userRole) {
		return config.mapped().map(userRole, UserRoleModel.class);

	}

	@Autowired
	@Qualifier("userRoleJpaRepository")
	private UserRoleJpaRepository userJpaRepository;
	
	
	@Override
	public List<UserRole> getListUsuarios() {
		return null;
	}

	@Override
	public UserRole addUsuario(UserRoleModel userRoleModel) {
		UserRole user = model2entity(userRoleModel);
		return userJpaRepository.save(user);
	}

}
