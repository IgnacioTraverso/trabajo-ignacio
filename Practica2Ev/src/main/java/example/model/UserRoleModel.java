package example.model;


public class UserRoleModel {
	private int userRoleId;
	private String role;
	private UserModel username;
	
	public UserRoleModel() {
		
	}
	
	public UserRoleModel(int userRoleId, String role, UserModel username) {
		super();
		this.userRoleId = userRoleId;
		this.role = role;
		this.username = username;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserModel getUser() {
		return username;
	}

	public void setUser(UserModel user) {
		this.username = user;
	}

	
}
