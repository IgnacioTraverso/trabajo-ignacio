package example.service.impl;

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
	@Qualifier("userRoleJpaRepository")
	private UserRoleJpaRepository userRoleJpaRepository;
	
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

	@Override
	public UserRole addUserRole(UserRoleModel userRoleModel) {
		UserRole userRole=model2entity(userRoleModel);
		return userRoleJpaRepository.save(userRole);
	}

}
