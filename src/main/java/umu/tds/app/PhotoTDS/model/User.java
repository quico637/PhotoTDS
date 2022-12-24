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
	private String descripcion;
	private String contrasena;
	private String profilePic;
	private boolean premium;
	private List<Notification> notifications;
	private List<Publication> publications;
	private List<User> usuariosSeguidores;
	private List<User> usuariosSeguidos;
	
	private final static int MIN_PASSWD_LENGTH = 6;
	
	public User(String username, String email, String nombreCompleto, Date fechaNacimiento, String descripcion,
			String contrasena, String profilePic, boolean premium, List<Notification> notifications, List<Publication> publications,
			List<User> usuariosSeguidores, List<User> usuariosSeguidos) {
		super();
		this.username = username;
		this.email = email;
		this.nombreCompleto = nombreCompleto;
		this.fechaNacimiento = fechaNacimiento;
		this.descripcion = descripcion;
		this.contrasena = contrasena;
		this.profilePic = profilePic;
		this.premium = premium;
		this.notifications = notifications;
		this.publications = publications;
		this.usuariosSeguidores = usuariosSeguidores;
		this.usuariosSeguidos = usuariosSeguidos;
	}

	public User(String username, String email, String nombreCompleto, Date fechaNacimiento, String descripcion, String contrasena, String profilePic) {
		super();
		this.username = username;
		this.email = email;
		this.nombreCompleto = nombreCompleto;
		this.fechaNacimiento = fechaNacimiento;
		this.descripcion = descripcion;
		this.contrasena = EncryptDecrypt.encrypt(contrasena);
		this.profilePic = profilePic;
		this.premium = false;
		this.notifications = new LinkedList<>();
		this.publications = new LinkedList<>();
		this.usuariosSeguidores = new LinkedList<>();
		this.usuariosSeguidos = new LinkedList<>();
		
//		this(username, email, nombreCompleto, fechaNacimiento, descripcion,
//				contrasena, profilePic, false, new LinkedList<Notification>(), new LinkedList<Publication>(), 
//				new LinkedList<User>(), new LinkedList<User>());
	}
	
	
	public boolean changePassword(String passwd) {
		if(passwd.length() < MIN_PASSWD_LENGTH)
			return false;
		
		
		this.contrasena = passwd;
		return true;
	}
	
	public boolean updateDescription(String desc) {
		if(this.descripcion.equals(desc))
			return false;
		this.descripcion = desc;
		return true;
	}
	
	public void updateProfilePic(String path) {
		this.profilePic = path;
	}
	
	
	// GETTERS AND SETTERS
	
	public List<Notification> getNotifications() {
		return new LinkedList<>(notifications);
	}

	public List<Publication> getPublications() {
		return new LinkedList<>(publications);
	}

	public List<User> getUsuariosSeguidores() {
		return new LinkedList<>(usuariosSeguidores);
	}


	public List<User> getUsuariosSeguidos() {
		return new LinkedList<>(usuariosSeguidos);
	}

	public void setCodigo(int c) {
		this.codigo = c;
	}
	
	public int getCodigo() {
		return this.codigo;
	}

	public String getProfilePic() {
		return this.profilePic;
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
	


	@Override
	public String toString() {
		return "User [codigo=" + codigo + ", username=" + username + ", email=" + email + ", nombreCompleto="
				+ nombreCompleto + ", fechaNacimiento=" + fechaNacimiento + ", descripcion=" + descripcion
				+ ", contrasena=" + contrasena + ", premium=" + premium + "]";
	}
		
}