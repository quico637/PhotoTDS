package umu.tds.app.PhotoTDS.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;

import umu.tds.app.PhotoTDS.model.EncryptDecrypt;
import umu.tds.app.PhotoTDS.model.Foto;
import umu.tds.app.PhotoTDS.model.HashTag;
import umu.tds.app.PhotoTDS.model.Publication;
import umu.tds.app.PhotoTDS.model.User;
import umu.tds.app.PhotoTDS.model.repositories.PublicationRepository;
import umu.tds.app.PhotoTDS.model.repositories.UserRepository;

/**
 * 
 * @author quico y JC
 *
 */
public class Controller {

	private static Controller unicaInstancia = null;
		
	private Map<String, List<Date>> logins;
	private Map<String, Boolean> valids;
	
	UserRepository userRepo;
	PublicationRepository pubRepo;
	
	

	private Controller() {
		this.logins = new HashMap<>();
		this.valids = new HashMap<>();
		inicializarCatalogos();
	}

	public static Controller getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new Controller();
		}

		return unicaInstancia;
	}

	private void inicializarCatalogos() {
		userRepo = UserRepository.getInstancia();
		pubRepo = PublicationRepository.getInstancia();
	}

	/**
	 * 
	 * @param username
	 * @param constrasena
	 * @return true if encrypted password is equal to stored password, if used is stored, and username / email is correct.
	 */
	public boolean login(String username, String constrasena) {
		Optional<User> usuario = UserRepository.getInstancia().getUser(username);
		
		System.out.println("Pre-PRE-moscas");
		if(usuario.isEmpty())
			return false;
		User u = usuario.get();
		
		List<String> emails = this.userRepo.getAllEmails();
		if(!emails.contains(username) && !username.equals(u.getUsername()))
			return false;
		
		
		if(!u.getContrasena().equals(EncryptDecrypt.encrypt(constrasena)))
			return false;
		
		List<Date> dates = this.logins.get(username);
		if(dates == null) {
			List<Date> l = new LinkedList<>();
			l.add(new Date());
			this.logins.put(username, l);
		}
		else {
			dates.add(new Date());
			this.logins.put(username, dates);
		}
		this.valids.put(username, true);
		System.out.println(this.logins);
		return true;
 
	}

	/**
	 * 
	 * @param username
	 * @param email
	 * @param nombreCompleto
	 * @param fechaNacimiento
	 * @param descripcion
	 * @param contrasena
	 * @param profilePic
	 * @return false if username already exists or if there is already been registered the same email.
	 */
	public boolean createUser(String username, String email, String nombreCompleto, Date fechaNacimiento,
			String descripcion, String contrasena, String profilePic) {
		Optional<User> user = this.userRepo.getUser(username);
		if(user.isPresent())
			return false;
				
		if (this.userRepo.getAllEmails().stream().anyMatch(e -> e.equals(email)))
			return false;
		
		User userNew = new User(username, email, nombreCompleto, fechaNacimiento, descripcion, contrasena, profilePic);
		userRepo.createrUser(userNew);
	
		return true;
	}
	
	/**
	 * 
	 * @param username
	 * @return Optional value containing user corresponding to the given username.
	 */
	public Optional<User> getUser(String username) {
		
		return this.userRepo.getUser(username);		
	}

	
	/**
	 * 
	 * @return all users from app
	 */
	public List<User> getAllusers() {
		userRepo.getAllUsers().stream().forEach(u -> System.out.println(u.toString()));
		
		// Avoids aliasing
		return new LinkedList<>(userRepo.getAllUsers());
	}
	
	/**
	 * 
	 * @return all publications, including photos and albums.
	 */
	public List<Publication> getAllPublications() {
		this.pubRepo.getAllPublications().stream().forEach(p -> System.out.println(p));
		return new LinkedList<>(pubRepo.getAllPublications());
	}

	
	/**
	 * 
	 * @param user
	 * @param path
	 * @return false if it cannot find profilePic in path. User class does not know if it can be showable or not.
	 */
	public boolean changeProfilePicture(String user, String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL == null)
			return false;
		
		Optional<User> u = this.userRepo.getUser(user); 
		u.ifPresent(a -> a.updateProfilePic(path));
		return true;
	}
	
	/**
	 * 
	 * @param user
	 * @param newDescription
	 * @return if it can change description or not.
	 */
	public boolean changeDescription(String user, String newDescription) {
		
		return this.userRepo.getUser(user).get().updateDescription(newDescription); 
	}
	
	/**
	 * checks if user has logged in, and returns its User instance from username / email String
	 * @param user
	 * @return
	 */
	private Optional<User> checkLoginAndGetUser(String user) {
		if(!this.logins.containsKey(user))
			return Optional.empty();
		
		if(!this.valids.get(user))
			return Optional.empty();
		
		Optional<User> u = this.userRepo.getUser(user);
		if(u.isPresent())
			return u;
		
		u = this.userRepo.getUserByEmail(user);
		return u;
	}
	
	/**
	 * leaves the app.
	 * @param u
	 */
	public void logout(String u) {
		this.valids.put(u, false);
	}
	
	/**
	 * 
	 * @param user
	 * @param passwd
	 * @return false if prev passwd is equal to new passwd or if it doesnt meet minimal passwd requirements.
	 */
	public boolean changePassword(String user, String passwd) {
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if(userOpt.isEmpty())
			return false;
		
		User u = userOpt.get();
		if(EncryptDecrypt.encrypt(passwd).equals(u.getContrasena()))
			return false;
		
		return u.changePassword(passwd);
	}

	/**
	 * creates a photo in the PhotoTDS domain and returns its object.
	 * @param titulo
	 * @param descripcion
	 * @param path
	 * @return boolean
	 */
	public boolean createFoto(String user, String titulo, String descripcion,  String path) {
		
		System.out.println("pre creating foto");
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if(userOpt.isEmpty())
			return false;
		
		User u = userOpt.get();
		
		System.out.println("creating foto");
		Foto f = new Foto(u.getUsername(), titulo, new Date(), descripcion, path);
		pubRepo.createPublication(f);
		return true;
	}
	
	/**
	 * We implement notification system through last logins.
	 * @param user
	 * @return
	 */
	public List<Publication> getPublicationsToShow(String user) {
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if(userOpt.isEmpty())
			return null;
		
		String u = userOpt.get().getUsername();
		
		
		List<Date> d = this.logins.get(u);
		int index;
		if(d.size() < 2)
			index = d.size() - 1;
		else {
			index = d.size() - 2;
			System.out.println("Es mayorq 2");
		}
		
		System.out.println("index: " + index);
		System.out.println(d);
		// pillo la vez anterior que entro.
		List<Publication> l = new LinkedList<>(this.pubRepo.getAllPublications().stream().
				filter(p -> p.getFechaPublicacion().after(d.get(index))).
				collect(Collectors.toList()));
		System.out.println("l: " + l);
		return l;
	}

}