package umu.tds.app.PhotoTDS.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;

import umu.tds.app.PhotoTDS.model.EncryptDecrypt;
import umu.tds.app.PhotoTDS.model.Foto;
import umu.tds.app.PhotoTDS.model.HashTag;
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
	private Optional<User> currentUser;
	
	UserRepository userRepo;
	PublicationRepository pubRepo;
	
	

	private Controller() {
		this.currentUser = Optional.empty();
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
	 * @return true if encrypted password is equal to stored password.
	 */
	public boolean login(String username, String constrasena) {
		Optional<User> usuario = UserRepository.getInstancia().getUser(username);
		
		System.out.println("Pre-PRE-moscas");
		if(usuario.isEmpty())
			return false;
		User u = usuario.get();

		return u.getContrasena().equals(EncryptDecrypt.encrypt(constrasena));
 
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
		this.currentUser = Optional.of(userNew);
		return true;
	}

	public List<User> getAllusers() {
		userRepo.getAllUsers().stream().forEach(u -> System.out.println(u.toString()));
		
		// Avoids aliasing
		return new LinkedList<>(userRepo.getAllUsers());
	}
	
	public void getAllPublications() {
		pubRepo.getAllPublications().stream().forEach(u -> System.out.println(u.toString()));
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
	 * 
	 * @param user
	 * @param passwd
	 * @return false if prev passwd is equal to new passwd or if it doesnt meet minimal passwd requirements.
	 */
	public boolean changePassword(String passwd) {
		User u = currentUser.get();
		if(EncryptDecrypt.encrypt(passwd).equals(u.getContrasena()))
			return false;
		
		return u.changePassword(passwd);
	}

	public void createFoto(String titulo, Date fechaPublicacion, String descripcion,  String path) {
		Foto f = new Foto(titulo, fechaPublicacion, descripcion, path);
		pubRepo.createPublication(f);
	}

}