package example.entity;

import javax.persistence.*;

@Entity
@Table(name="user_role", uniqueConstraints=@UniqueConstraint(columnNames= {"role","username"}))
public class UserRole {

	@Id
	@GeneratedValue
	@Column(name="user_role_id", unique=true, nullable=false)
	private int userRoleId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="username", nullable=false)
	private User user;
	
	@Column(name="role", nullable=false, length=45)
	private String role;

	public UserRole() {}
	
	public UserRole(int userRoleId, User user, String role) 
	{
		super();
		this.userRoleId = userRoleId;
		this.user = user;
		this.role = role;
	}
	
	//Getters and setters
	public int getUserRoleId() 
	{
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) 
	{
		this.userRoleId = userRoleId;
	}

	public User getUser() 
	{
		return user;
	}

	public void setUser(User user) 
	{
		this.user = user;
	}

	public String getRole() 
	{
		return role;
	}

	public void setRole(String role) 
	{
		this.role = role;
	}
	
}
