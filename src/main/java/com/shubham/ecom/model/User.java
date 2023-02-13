package com.shubham.ecom.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.persistence.JoinColumn;

import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
//	comes from javax.validation.constraints
	@NotEmpty
	@Column(nullable = false)
	private String firstName;
	
	private String lastName;
	
//	its is candidate key in some sense because it has notnull and unique constraints
	@Column(nullable = false, unique = true)
	@NotEmpty
//	With @Email annotation we can check if email is wrong or not and if it is wrong then given message with curly bracket with be given
	@Email(message = "{errors.invalid_email")
	private String email;
	
	private String password;
	
//	here we are checking users roll if he is admin or user or both
//	cascade = here we are using cascade for if we delete specific roll then users with that rolls will also get deleted
//	Eg = if user is deleted then orders of that user will also get deleted
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//	With many to many and many to one relation the third table is created for joining relations 
//	with JoinTable annotations we are joining User-ID and Role_Id table to establish relation between them
	@JoinTable(
			name = "user_role",
			joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")}, 
					inverseJoinColumns = {@JoinColumn (name = "ROLE_ID", referencedColumnName = "ID")}
			)
//	Java Development Tools UI
	
	private List<Role> roles;

	public User(User user) {
		super();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.roles = user.getRoles();
	}
	
	public User() {
		
	}
	
	

}
