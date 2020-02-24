package example.service;


import example.entity.UserRole;
import example.model.UserRoleModel;

public interface UserRoleService {
	public abstract UserRole model2entity(UserRoleModel userRoleModel);
	public abstract UserRoleModel entity2model(UserRole userRole);
	public abstract UserRole addUserRole(UserRoleModel userRoleModel);
}
