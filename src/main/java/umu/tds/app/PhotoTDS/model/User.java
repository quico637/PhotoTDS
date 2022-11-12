package umu.tds.app.PhotoTDS.model;

import java.util.Date;
import umu.tds.app.PhotoTDS.model.EncryptDecrypt;


public class User {

	private int codigo;
	private final String username;
	private final String email;
	private final String nombreCompleto;
	private final Date fechaNacimiento;
	private final String descripcion;
	private final String contrasena;
	private boolean premium;
	
	public User(String username, String email, String nombreCompleto, Date fechaNacimiento, String descripcion, String contrasena) {
		super();
		this.username = username;
		this.email = email;
		this.nombreCompleto = nombreCompleto;
		this.fechaNacimiento = fechaNacimiento;
		this.descripcion = descripcion;
		this.contrasena = EncryptDecrypt.encrypt(contrasena);
	}
	
	public void setCodigo(int c) {
		this.codigo = c;
	}
	
	public int getCodigo() {
		return this.codigo;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getContrasena() {
		return contrasena;
	}
	
	public boolean isPremium() {
		return premium;
	}
	
	public void setPremium(boolean premium) {
		this.premium = premium;
	}
		
	@Override
	public String toString() {
		return "User [usuario=" + username + ", email=" + email + "]";
	}
	
}