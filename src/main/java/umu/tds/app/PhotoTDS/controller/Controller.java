package umu.tds.app.PhotoTDS.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import umu.tds.app.PhotoTDS.model.EncryptDecrypt;
import umu.tds.app.PhotoTDS.model.Foto;
import umu.tds.app.PhotoTDS.model.HashTag;
import umu.tds.app.PhotoTDS.model.User;
import umu.tds.app.PhotoTDS.model.repositories.PublicationRepository;
import umu.tds.app.PhotoTDS.model.repositories.UserRepository;

public class Controller {

	private static Controller unicaInstancia = null;

	UserRepository userRepo;
	PublicationRepository pubRepo;

	private Controller() {
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

	public boolean login(String username, String constrasena) {
		User usuario = UserRepository.getInstancia().getUser(username);
		if (usuario == null) {
			return false;
		}
		if(EncryptDecrypt.encrypt(usuario.getContrasena()).equals(EncryptDecrypt.encrypt(constrasena))) {
			return false;
		}
		return true;
	}

	public void createUser(String username, String email, String nombreCompleto, Date fechaNacimiento,
			String descripcion, String contrasena) {
		User u = new User(username, email, nombreCompleto, fechaNacimiento, descripcion, contrasena);
		userRepo.createrUser(u);
	}

	public List<User> getAllusers() {
		userRepo.getAllUsers().stream().forEach(u -> System.out.println(u.toString()));
		return new LinkedList<>(userRepo.getAllUsers());
	}
	
	public void getAllPublications() {
		pubRepo.getAllPublications().stream().forEach(u -> System.out.println(u.toString()));
	}
	
	private List<HashTag> getHashTagsFromText(String text) {
		List<HashTag> lh = new LinkedList<>();
		String[] listaPalabras = text.split(" "); 
		for(String palabra : listaPalabras) {
			if(palabra.charAt(0) == '#') {
				HashTag has = HashTag.createHashtag(palabra);
				if(has != null) {
					lh.add(has);
				}
			}
		}
		return lh;
	}
	
	public void createFoto(String titulo, Date fechaPublicacion, String descripcion,  String path) {
		List<HashTag> hashtags = getHashTagsFromText(descripcion);
		Foto f = new Foto(titulo, fechaPublicacion, descripcion, hashtags, path);
		pubRepo.createPublication(f);
	}

}