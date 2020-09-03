package br.com.fiap.EpicTask.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity (name = "users" )
//@Data [Lombok] - cria todos os Getters and Setters
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank (message = "{user.name.empty}")
	private String name;
	
	@NotBlank (message = "{user.email.empty}")
	@Email (message = "{user.email.valid}")
	private String email;
	
	@Size(min = 8, message = "{user.pass.size}")
	private String pass;
	
	@NotBlank(message = "{user.gitName.empty}")
	private String gitName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getGitName() {
		return gitName;
	}
	public void setGitName(String gitName) {
		this.gitName = gitName;
	}
	@Override
	public String toString() {
		return "Nome = " + this.name +
				" (" + this.email + ")";
	}
	
}