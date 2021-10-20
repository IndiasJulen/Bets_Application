package domain;

import javax.persistence.Entity;

@Entity
public class Admin extends User{
	
	public Admin(String izena, String pass) {
		super(izena, pass);
		// TODO Auto-generated constructor stub
	}
	

}
