package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private String Izena;
	private String Pass;
	
	public User() {
		super();
	}
	
	public User(String izena, String pass) {
		super();
		this.Izena = izena;
		this.Pass = pass;
	}


	public String getIzena() {
		return Izena;
	}
	public void setIzena(String izena) {
		Izena = izena;
	}
	public String getPass() {
		return Pass;
	}
	public void setPass(String pass) {
		Pass = pass;
	}

}
