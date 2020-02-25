package example.service;

import java.util.List;
import example.entity.User;
import example.model.UserModel;

public interface UserService {
	public abstract User model2entity(UserModel userModel);
	public abstract UserModel entity2model(User user);
	public abstract List<User> getListUsuarios();
	public abstract User addUsuario(UserModel userModel);
}
