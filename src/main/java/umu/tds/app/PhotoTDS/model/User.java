package umu.tds.app.PhotoTDS.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class User {

	private int codigo;
	private final String username;
	private final String email;
	private final String nombreCompleto;
	private final Date fechaNacimiento;
	private final String descripcion;
	private final String contrasena;
	private boolean premium;
	private List<Notification> notifications;
	private List<Publication> publications;
	private List<User> usuariosSeguidores;
	
	public User(String username, String email, String nombreCompleto, Date fechaNacimiento, String descripcion,
			String contrasena, boolean premium, List<Notification> notifications, List<Publication> publications,
			List<User> usuariosSeguidores) {
		super();
		this.username = username;
		this.email = email;
		this.nombreCompleto = nombreCompleto;
		this.fechaNacimiento = fechaNacimiento;
		this.descripcion = descripcion;
		this.contrasena = contrasena;
		this.premium = premium;
		this.notifications = notifications;
		this.publications = publications;
		this.usuariosSeguidores = usuariosSeguidores;
	}

	public User(String username, String email, String nombreCompleto, Date fechaNacimiento, String descripcion, String contrasena) {
		super();
		this.username = username;
		this.email = email;
		this.nombreCompleto = nombreCompleto;
		this.fechaNacimiento = fechaNacimiento;
		this.descripcion = descripcion;
		this.contrasena = EncryptDecrypt.encrypt(contrasena);
		this.premium = false;
		this.notifications = new LinkedList<>();
		this.publications = new LinkedList<>();
		this.usuariosSeguidores = new LinkedList<>();
	}
	
	public List<Notification> getNotifications() {
		return new LinkedList<>(notifications);
	}

	public List<Publication> getPublications() {
		return new LinkedList<>(publications);
	}

	public List<User> getUsuariosSeguidores() {
		return new LinkedList<>(usuariosSeguidores);
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
		return "User [codigo=" + codigo + ", username=" + username + ", email=" + email + ", nombreCompleto="
				+ nombreCompleto + ", fechaNacimiento=" + fechaNacimiento + ", descripcion=" + descripcion
				+ ", contrasena=" + contrasena + ", premium=" + premium + "]";
	}
		
}