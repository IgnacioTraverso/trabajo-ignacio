package example.service;


import java.util.List;

import example.entity.UserRole;
import example.model.UserRoleModel;

public interface UserRoleService {
	
	public abstract UserRole model2entity(UserRoleModel userRoleModel);
	public abstract UserRoleModel entity2model(UserRole userRole);
	public abstract List<UserRole> getListUsuarios();
	public abstract UserRole addUsuario(UserRoleModel userRoleModel);
}
