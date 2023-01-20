package umu.tds.app.PhotoTDS.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


import umu.tds.app.PhotoTDS.model.Album;
import umu.tds.app.PhotoTDS.model.EncryptDecrypt;
import umu.tds.app.PhotoTDS.model.Foto;
import umu.tds.app.PhotoTDS.model.Publication;
import umu.tds.app.PhotoTDS.model.User;
import umu.tds.app.PhotoTDS.model.Utils;
import umu.tds.app.PhotoTDS.model.repositories.PublicationRepository;
import umu.tds.app.PhotoTDS.model.repositories.UserRepository;

/**
 * 
 * @author quico y JC
 *
 */
public class Controller implements PropertyChangeListener {

	private static Controller unicaInstancia = null;

	private Map<String, List<Date>> logins;
	private Map<String, Boolean> valids;

	private UserRepository userRepo;
	private PublicationRepository pubRepo;

	private Optional<User> currentuser;

	private Controller() {
		this.logins = new HashMap<>();
		this.valids = new HashMap<>();
		umu.tds.fotos.CargadorFotos.getUnicaInstancia().addCancionesListener(this);
		inicializarCatalogos();

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

		umu.tds.fotos.Fotos fotos = umu.tds.fotos.MapperFotosXMLtoJava.cargarFotos(evt.getNewValue().toString());
		if (this.currentuser.isEmpty())
			return;
		User u = this.currentuser.get();
		
		for (umu.tds.fotos.Foto f : fotos.getFoto()) {
			Foto foto = u.createPhoto(f.getTitulo(), f.getDescripcion(), f.getPath(), f.getHashTags().get(0).getHashTag());

			this.pubRepo.createPublication(foto);
			this.userRepo.updateUser(u);

		}

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
	 * checks if user has logged in, and returns its User instance from username /
	 * email String
	 * 
	 * @param user
	 * @return
	 */
	private Optional<User> checkLoginAndGetUser(String user) {
		if (!this.logins.containsKey(user))
			return Optional.empty();

		if (!this.valids.get(user))
			return Optional.empty();

		Optional<User> u = this.userRepo.getUser(user);
		if (u.isPresent())
			return u;

		u = this.userRepo.getUserByEmail(user);
		return u;
	}

	/**
	 * 
	 * @param username
	 * @param constrasena
	 * @return true if encrypted password is equal to stored password, if used is
	 *         stored, and username / email is correct.
	 */
	public boolean login(String username, String constrasena) {
		Optional<User> usuario = UserRepository.getInstancia().getUser(username);

		if (usuario.isEmpty())
			return false;
		User u = usuario.get();

		List<String> emails = this.userRepo.getAllEmails();
		if (!emails.contains(username) && !username.equals(u.getUsername()))
			return false;

		if (!u.getContrasena().equals(EncryptDecrypt.encrypt(constrasena)))
			return false;

		List<Date> dates = this.logins.get(username);
		if (dates == null) {
			List<Date> l = new LinkedList<>();
			l.add(new Date());
			this.logins.put(username, l);
		} else {
			dates.add(new Date());
			this.logins.put(username, dates);
		}
		this.valids.put(username, true);
		this.currentuser = Optional.ofNullable(u);

		return true;

	}
	
	/**
	 * leaves the app.
	 * 
	 * @param u
	 */
	public Boolean logout(String user) {
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;
		User u = userOpt.get();
		u.setUltimoLogin(new Date());
		this.userRepo.updateUser(u);
		this.valids.put(user, false);
		this.currentuser = Optional.empty();
		return true;
	}

	/**
	 * creates user 
	 * @param username
	 * @param email
	 * @param nombreCompleto
	 * @param fechaNacimiento
	 * @param descripcion
	 * @param contrasena
	 * @param profilePic
	 * @return false if username already exists or if there is already been
	 *         registered the same email.
	 */
	public boolean createUser(String username, String email, String nombreCompleto, Date fechaNacimiento,
			String descripcion, String contrasena, String profilePic) {

		Optional<User> user = this.userRepo.getUser(username);
		if (user.isPresent())
			return false;

		if (this.userRepo.getAllEmails().stream().anyMatch(e -> e.equals(email)))
			return false;

		User userNew = new User(username, email, nombreCompleto, fechaNacimiento, descripcion,
				EncryptDecrypt.encrypt(contrasena), profilePic, new Date());
		userRepo.createrUser(userNew);

		return true;
	}

	/**
	 * get user from string
	 * @param username
	 * @return Optional value containing user corresponding to the given username.
	 */
	public Optional<User> getUser(String username) {

		return this.userRepo.getUser(username);
	}

	/**
	 * get all user
	 * @return all users from app
	 */
	public List<User> getAllusers() {
		// Avoids aliasing
		return new LinkedList<>(userRepo.getAllUsers());
	}

	/**
	 * 
	 * @return all publications, including photos and albums.
	 */
	public List<Publication> getAllPublications() {
		return new LinkedList<>(pubRepo.getAllPublications());
	}
	
	/**
	 * We implement notification system through last logins.
	 * 
	 * @param user
	 * @return
	 */
	public List<Publication> getPublicationsToShow(String user) {
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return null;

		User us =  userOpt.get();		

		// pillo la vez anterior que entro.
		List<Publication> l = this.pubRepo.getPublicationsFromFollowers(us).stream()
			.filter(p -> p.getFechaPublicacion().after(us.getUltimoLogin()))
			.filter(p -> us.checkPublicationInAlbums(p))
			.collect(Collectors.toList());

		return l;
	}

	/**
	 * Method for finding people or hashtags
	 * 
	 * @param user
	 * @param b
	 * @return
	 */
	public List<Object> getBusqueda(String user, String b) {

		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return null;

		List<Object> l = new LinkedList<>();

		if (Utils.isHashtag(b)) {
			l = this.pubRepo.getPublicationsFromHtg(Utils.getHashTagFromFinder(b));
			return l;
		}

		else {
			Optional<User> u = this.userRepo.getUser(b);
			if (u.isPresent()) {
				l.add(u.get());
				return l;
			}

			u = this.userRepo.getUserByEmail(b);
			if (u.isPresent()) {
				l.add(u.get());
				return l;
			}

			u = this.userRepo.getUserFullName(b);
			if (u.isPresent()) {
				l.add(u.get());
				return l;
			}

		}
		return null;
	}


	/**
	 * 
	 * @param user
	 * @param path
	 * @return false if it cannot find profilePic in path. User class does not know
	 *         if it can be showable or not.
	 */
	public boolean changeProfilePicture(String user, String path) {
		Optional<User> u = this.userRepo.getUser(user);
		u.ifPresent(a -> a.updateProfilePic(path));
		this.userRepo.getUser(user).get().updateProfilePic(path);
		System.out.println(path);
		this.userRepo.updateUser(u.get());
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
	 * @return false if prev passwd is equal to new passwd or if it doesnt meet
	 *         minimal passwd requirements.
	 */
	public boolean changePassword(String user, String passwd) {
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;

		User u = userOpt.get();
		if (EncryptDecrypt.encrypt(passwd).equals(u.getContrasena()))
			return false;

		Boolean b = u.changePassword(passwd);
		this.userRepo.updateUser(u);
		return b;
	}

	/**
	 * adds one like to pub.
	 * @param pub
	 * @param user
	 * @return
	 */
	public boolean meGusta(Publication pub, String user) {
	
		pub.addMeGusta();
		this.pubRepo.updatePublication(pub);

		return true;
	}

	/**
	 * adds comment to publication
	 * @param pub
	 * @param comentario
	 * @param user
	 * @return
	 */
	public boolean addComentario(Publication pub, String comentario, String user) {

		pub.anadirComentarios(comentario, this.userRepo.getUser(user).get().getUsername());
		this.pubRepo.updatePublication(pub);
		return true;
	}

	/**
	 * creates a photo in the PhotoTDS domain and returns its object.
	 * 
	 * @param titulo
	 * @param descripcion
	 * @param path
	 * @return boolean
	 */
	public boolean createFoto(String user, String titulo, String descripcion, String path) {

		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;

		User u = userOpt.get();

		Foto f = u.createPhoto(titulo, descripcion, path);

		this.pubRepo.createPublication(f);
		this.userRepo.updateUser(u);
		return true;
	}

	/**
	 * Must be logged in.
	 * 
	 * @param user
	 * @param p
	 * @return
	 */
	public boolean removePublication(String user, Publication p) {
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;

		User u = userOpt.get();
		
		u.removePublication(p);
		
		this.pubRepo.removePublication(p);
		this.userRepo.updateUser(u);
		return true;
	}

	public boolean createAlbum(String user, String titulo, String descripcion, String path) {

		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;

		User u = userOpt.get();

		Album a = u.createAlbum(titulo, descripcion, path);

		this.pubRepo.createPublication(a);
		this.userRepo.updateUser(u);
		return true;
	}

	/**
	 * updates some user's account to premium.
	 * 
	 * @param user
	 * @return success or failure.
	 */
	public boolean goPremium(String user) {
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;

		User u = userOpt.get();
		u.goPremium();
		this.userRepo.updateUser(u);
		return true;

	}

	/**
	 * User must be logged in.
	 * 
	 * @param u
	 * @param f
	 */
	public void uploadPhotos(String u, String f) {

		Optional<User> userOpt = checkLoginAndGetUser(u);
		if (userOpt.isEmpty())
			return;

		umu.tds.fotos.CargadorFotos.getUnicaInstancia().setArchivoCanciones(f);

	}

	/**
	 * Only premium
	 * @param user
	 * @param path
	 * @return
	 */
	public boolean createExcel(String user, String path) {

		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;

		User u = userOpt.get();

		if (!u.isPremium())
			return false;
		
		ExcelGenerator.generateExcel(u, path, u.getUsuariosSeguidores());
		return true;
	}

	/**
	 * Only premium
	 * @param user
	 * @param path
	 * @return
	 */
	public boolean createPdf(String user, String path) {
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;

		User u = userOpt.get();

		if (!u.isPremium())
			return false;

		PDFGenerator.generatePDF(user, path, u.getUsuariosSeguidores());
		return true;
	}
	
	/**
	 * Premium functionality.
	 * @param user
	 * @return
	 */
	public List<Publication> getMoreLikedFotos(String user) {

		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return null;

		User u = userOpt.get();
		if(!u.isPremium())
			return null;

		return u.getPublications().stream()
				.sorted(Comparator.comparing(Publication::getLikes).reversed()) 
				.limit(User.NUM_LIKES_PREMIUM)
				.collect(Collectors.toList());

	}
	
	/**
	 * PRE Premium functionalty.
	 * @param user
	 * @return
	 */
	public List<Publication> lastLikedFotos(String user) {

		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return null;

		User u = userOpt.get();

		return u.getPublications().stream()
				.filter(p -> p.getLikes()>0)
				.sorted(Comparator.comparing(Publication::getFechaPublicacion).reversed()) 
				.limit(User.LAST_LIKED_PICTURES)
				.collect(Collectors.toList());

	}

	/**
	 * user follows new "follow-user"
	 * @param user
	 * @param follow
	 * @return
	 */
	public boolean follow(String user, String follow) {
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;

		User u = userOpt.get();

		Optional<User> us = this.userRepo.getUser(user);

		if (us.isEmpty())
			return false;

		Optional<User> newFollowedOpt = this.userRepo.getUser(follow);
		if (newFollowedOpt.isEmpty())
			return false;

		User newFollowed = newFollowedOpt.get();

		if (u.follow(newFollowed))
			this.userRepo.updateUser(u);

		if (newFollowed.addFollower(u))
			this.userRepo.updateUser(newFollowed);

		return true;
	}
	
	/**
	 * user unfollows "unfollow-user"
	 * @param user
	 * @param unfollow
	 * @return
	 */
	public boolean unfollow(String user, String unfollow) {
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;

		User u = userOpt.get();

		Optional<User> us = this.userRepo.getUser(user);

		if (us.isEmpty())
			return false;

		Optional<User> newUnfollowedOpt = this.userRepo.getUser(unfollow);
		if (newUnfollowedOpt.isEmpty())
			return false;

		User newFollowed = newUnfollowedOpt.get();

		if (u.unfollow(newFollowed))
			this.userRepo.updateUser(u);

		if (newFollowed.removeFollower(u))
			this.userRepo.updateUser(newFollowed);

		return true;
	}
	
	public Optional<Publication> getPublication(String user, String title) {		
		return this.pubRepo.getPublication(title);	
	}
	
	/**
	 * Fotos to show in user's profile.
	 * @param user
	 * @return
	 */
	public List<Foto> getFotosProfile(String user) {
		Optional<User> userOpt = this.userRepo.getUser(user);
		if(userOpt.isEmpty())
			return null;
		
		User u = userOpt.get();
		
		return this.pubRepo.getAllPublicationsUser(u.getUsername()).stream()
				.filter(p -> p instanceof Foto)
				.filter(p -> u.isFotoInAlbum((Foto) p))
				.map(p -> (Foto) p)
				.collect(Collectors.toList());
	}
	
	/**
	 * Albums to show in user's albums profile
	 * @param user
	 * @return
	 */
	public List<Album> getAlbumsProfile(String user) {
		Optional<User> userOpt = this.userRepo.getUser(user);
		if(userOpt.isEmpty())
			return null;
		
		User u = userOpt.get();
		
		return this.pubRepo.getAllPublicationsUser(u.getUsername()).stream()
				.filter(p -> p instanceof Album)
				.map(p -> (Album) p)
				.collect(Collectors.toList());
	}
		
	/**
	 * adds new picture to album
	 * @param user
	 * @param a
	 * @param titulo
	 * @param descripcion
	 * @param path
	 * @return
	 */
	public boolean addNewPicture(String user, Album a, String titulo, String descripcion, String path) {
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;
		
		User u = userOpt.get();
		Foto f = u.addFotoAlbum(a, titulo, descripcion, path);

		this.pubRepo.createPublication(f);
		this.pubRepo.updatePublication(a);
		this.userRepo.updateUser(u);
		return true;
	}

	/**
	 * Checks if f is following u
	 * 
	 * @param u
	 * @param f
	 * @return true or false
	 */
	public boolean checkFollower(String f, String user) {
		User u = this.userRepo.getUser(user).get();

		Optional<User> followerOp = this.userRepo.getUser(f);
		if (followerOp.isEmpty())
			return false;

		return u.getUsuariosSeguidores().stream()
				.map(User::getUsername)
				.anyMatch(un -> un.equals(f));
	}
	
	

}