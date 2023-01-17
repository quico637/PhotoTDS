package umu.tds.app.PhotoTDS.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import umu.tds.app.PhotoTDS.model.Album;
import umu.tds.app.PhotoTDS.model.Comentario;
import umu.tds.app.PhotoTDS.model.EncryptDecrypt;
import umu.tds.app.PhotoTDS.model.Foto;
import umu.tds.app.PhotoTDS.model.HashTag;
import umu.tds.app.PhotoTDS.model.Publication;
import umu.tds.app.PhotoTDS.model.User;
import umu.tds.app.PhotoTDS.model.Utils;
import umu.tds.app.PhotoTDS.model.repositories.PublicationRepository;
import umu.tds.app.PhotoTDS.model.repositories.UserRepository;
import umu.tds.fotos.CargadorFotos;
import umu.tds.fotos.MapperFotosXMLtoJava;

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
		CargadorFotos.getUnicaInstancia().addCancionesListener(this);
		inicializarCatalogos();

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

		umu.tds.fotos.Fotos fotos = MapperFotosXMLtoJava.cargarFotos(evt.getNewValue().toString());
		for (umu.tds.fotos.Foto f : fotos.getFoto()) {
			if (this.currentuser.isEmpty())
				continue;
			User u = this.currentuser.get();
			Foto ph = new Foto(u.getUsername(), f.getTitulo(), new Date(), f.getDescripcion(), f.getPath());
			this.pubRepo.createPublication(ph);
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
	 * 
	 * @param username
	 * @param constrasena
	 * @return true if encrypted password is equal to stored password, if used is
	 *         stored, and username / email is correct.
	 */
	public boolean login(String username, String constrasena) {
		Optional<User> usuario = UserRepository.getInstancia().getUser(username);

		System.out.println("Pre-PRE-moscas");
		if (usuario.isEmpty())
			return false;
		User u = usuario.get();

		List<String> emails = this.userRepo.getAllEmails();
		if (!emails.contains(username) && !username.equals(u.getUsername()))
			return false;

		System.out.println("moscas22222");
		System.out.println(constrasena);
		System.out.println(EncryptDecrypt.decrypt(u.getContrasena()));
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

	public boolean meGusta(Publication pub, String user) {
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;

		pub.addMeGusta();
		this.pubRepo.updatePublication(pub);

		return true;
	}

	public boolean addComentario(Publication pub, String comentario, String user) {
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;
		pub.anadirComentarios(comentario, userOpt.get().getUsername());
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

		System.out.println("pre creating foto");
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;

		User u = userOpt.get();

		Foto f = u.createPhoto(titulo, descripcion, path);

		System.out.println("creating foto");
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
		this.userRepo.updateUser(u);
		this.pubRepo.removePublication(p);
		return true;
	}

	public boolean createAlbum(String user, String titulo, String descripcion, String path) {

		System.out.println("pre creating album");
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;

		User u = userOpt.get();

		Album a = u.createAlbum(titulo, descripcion, path);

		System.out.println("creating album");
//		this.pubRepo.createPublication(f);
		this.pubRepo.createPublication(a);
		this.userRepo.updateUser(u);
		return true;
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

		String u = userOpt.get().getUsername();

		List<Date> d = this.logins.get(u);
		int index;
		if (d.size() < 2)
			index = d.size() - 1;
		else {
			index = d.size() - 2;
			System.out.println("Es mayorq 2");
		}

		System.out.println("index: " + index);
		System.out.println(d);

		// pillo la vez anterior que entro.
		List<Publication> l = new LinkedList<>(this.pubRepo.getAllPublications().stream().
		// filter(p -> p.getFechaPublicacion().after(userOpt.get().getUltimoLogin())).
				collect(Collectors.toList()));

		for (Publication p : l) {
			System.out.println("[PUB]: " + p.getTitulo() + p.getHashTags());
		}

		System.out.println("l: " + l);
		return l;
	}

	// TBD busqueda hashtags

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
			for (HashTag h : Utils.getHashTagFromFinder(b))
				l.addAll(this.pubRepo.getPublicationsFromHtg(h));
			System.out.println("Hashtags: " + l);
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

		CargadorFotos.getUnicaInstancia().setArchivoCanciones(f);

	}

	public boolean createExcel(String user, String path) {

		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;

		User u = userOpt.get();

		if (!u.isPremium())
			return false;

		try {
			// declare file name to be create
			String filename = path;
			// creating an instance of HSSFWorkbook class
			HSSFWorkbook workbook = new HSSFWorkbook();
			// invoking creatSheet() method and passing the name of the sheet to be created
			HSSFSheet sheet = workbook.createSheet("Users");
			// creating the 0th row using the createRow() method
			HSSFRow rowhead = sheet.createRow((short) 0);
			// creating cell by using the createCell() method and setting the values to the
			// cell by using the setCellValue() method
			rowhead.createCell(1).setCellValue("username");
			rowhead.createCell(2).setCellValue("e-mail");
			rowhead.createCell(3).setCellValue("presentation");

			int n = 1;
			for (User f : u.getUsuariosSeguidores()) {
				// creating the 1st row
				HSSFRow row = sheet.createRow((short) n);
				// inserting data in the first row
				row.createCell(1).setCellValue(f.getUsername());
				row.createCell(2).setCellValue(f.getEmail());
				row.createCell(3).setCellValue(f.getDescripcion());
				n++;
			}

			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			// closing the Stream
			fileOut.close();
			// closing the workbook
			workbook.close();
			// prints the message on the console
			System.out.println("Excel file has been generated successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean createPdf(String user, String path) {
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;

		User u = userOpt.get();
//
//		if (!u.isPremium())
//			return false;

		System.out.println("PRE PDF!");

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		document.open();
		PdfPTable table = new PdfPTable(4);

		table.addCell("User-Nº");
		table.addCell("username");
		table.addCell("e-mail");
		table.addCell("description");

		int n = 1;
		for (User f : u.getUsuariosSeguidores()) {

			System.out.println("[SEGUIDOR] " + f);
			// creating the 1st row
			table.addCell(Integer.toString(n));
			// inserting data in the first row
			table.addCell(f.getUsername());
			table.addCell(f.getEmail());
			table.addCell(f.getDescripcion());
			n++;
		}

		try {
			document.add(table);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.close();

		System.out.println("Sucess PDF!");
		return true;
	}

	public boolean follow(String user, String follow) {
		System.out.println("follow(): user -  " + user + "follow - " + follow);
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return false;

		User u = userOpt.get();

		Optional<User> us = this.userRepo.getUser(user);

		if (us.isEmpty()) {
			System.out.println("us false");
			return false;
		}

		Optional<User> newFollowedOpt = this.userRepo.getUser(follow);
		if (newFollowedOpt.isEmpty()) {
			System.out.println("newFollowedOpt false");
			return false;
		}

		User newFollowed = newFollowedOpt.get();

		if (u.follow(newFollowed))
			this.userRepo.updateUser(u);

		if (newFollowed.addFollower(u))
			this.userRepo.updateUser(newFollowed);

		return true;
	}

	public List<Publication> getMoreLikedFotos(String user) {

		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty())
			return null;

		User u = userOpt.get();

		return u.getPublications().stream()
				.sorted(Comparator.comparing(Publication::getLikes).reversed()) 
				.limit(User.NUM_LIKES_PREMIUM)
				.collect(Collectors.toList());

	}

	/**
	 * Checks if f is following u
	 * 
	 * @param u
	 * @param f
	 * @return true or false
	 */
	public boolean checkFollower(String user, String f) {

		// check if user is logged
		Optional<User> userOpt = checkLoginAndGetUser(user);
		if (userOpt.isEmpty()) {
			System.out.println("checkFollower(): user not logged");
			return false;
		}

		User u = userOpt.get();

		Optional<User> followerOp = this.userRepo.getUser(f);
		if (followerOp.isEmpty()) {
			System.out.println("checkFollower(): follower not logged");
			return false;
		}

		User follower = followerOp.get();

		return u.getUsuariosSeguidores().contains(follower);
	}

}