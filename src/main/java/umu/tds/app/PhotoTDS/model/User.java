package umu.tds.app.PhotoTDS.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import umu.tds.app.PhotoTDS.model.discounts.AgeDiscount;
import umu.tds.app.PhotoTDS.model.discounts.CompoundDiscount;
import umu.tds.app.PhotoTDS.model.discounts.Discount;
import umu.tds.app.PhotoTDS.model.discounts.PopularityDiscount;


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
	private Date ultimoLogin;
	
	private Discount dc;
	
	
	private final static int DEFAULT_PRICE_PREMIUM = 10;
	private final static int MIN_PASSWD_LENGTH = 6;
	
	public User(String username, String email, String nombreCompleto, Date fechaNacimiento, String descripcion,
			String contrasena, String profilePic, boolean premium, List<Notification> notifications, List<Publication> publications,
			List<User> usuariosSeguidores, List<User> usuariosSeguidos, Date ultimoLogin) {
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
		this.ultimoLogin = ultimoLogin;

		List<Discount> l = new LinkedList<>();
		for(Discount d : Discount.getPossibleDiscounts()) {
			if(d.usable(this)) {
				this.dc = d;
				l.add(d);
			}
		}
		
		if(l.size() > 1) 
			this.dc = new CompoundDiscount(l);
		
	}

	public User(String username, String email, String nombreCompleto, Date fechaNacimiento, String descripcion, 
			String contrasena, String profilePic, Date ultimoLogin) {
		
		this(username, email, nombreCompleto, fechaNacimiento, descripcion,
				EncryptDecrypt.encrypt(contrasena), profilePic, false, new LinkedList<Notification>(), new LinkedList<Publication>(), 
				new LinkedList<User>(), new LinkedList<User>(), ultimoLogin);
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
	
	public boolean updateProfilePic(String path) {
		if(this.profilePic.equals(path))
			return false;
		this.profilePic = path;
		return true;
	}
	
	
	public int getAgeYears() {
		return Period.between(this.fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();
	}
	
	public void goPremium() {
		this.premium = true;
	}
	
	public boolean follow(User u) {
		if(this.usuariosSeguidos.contains(u))
			return false;
		
		this.usuariosSeguidos.add(u);
		return true;
	}
	
	public boolean addFollower(User f) {
		if(this.usuariosSeguidores.contains(f))
			return false;
		
		this.usuariosSeguidores.add(f);
		return true;
	}
	
	public Foto createPhoto(String titulo, String descripcion, String path) {
		Foto f = new Foto(this.username, titulo, new Date(), descripcion, path);
		this.publications.add(f);
		return f;
	}

	// CALCULATED PROPERTIES.
	
	public int getNumFollowers() {
		return this.usuariosSeguidores.size();
	}
	
	public int getNumFollowing() {
		return this.usuariosSeguidos.size();
	}

	
	public int getTotalPremiumPrice() {
		return this.dc.getDiscount(DEFAULT_PRICE_PREMIUM, this);
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
	
	public Date getUltimoLogin() {
		return ultimoLogin;
	}
	
	public void setUltimoLogin(Date ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

//	@Override
//	public String toString() {
//		return "User [codigo=" + codigo + ", username=" + username + ", email=" + email + ", nombreCompleto="
//				+ nombreCompleto + ", fechaNacimiento=" + fechaNacimiento + ", descripcion=" + descripcion
//				+ ", contrasena=" + contrasena + ", profilePic=" + profilePic + ", premium=" + premium
//				+ ", notifications=" + notifications + ", publications=" + publications + ", usuariosSeguidores="
//				+ usuariosSeguidores + ", usuariosSeguidos=" + usuariosSeguidos + ", ultimoLogin=" + ultimoLogin
//				+ ", dc=" + dc + "]";
//	}


		
}