package umu.tds.app.PhotoTDS.controller;

import java.util.Date;

import umu.tds.app.PhotoTDS.model.EncryptDecrypt;
import umu.tds.app.PhotoTDS.model.User;
import umu.tds.app.PhotoTDS.model.repositories.UserRepository;

public class Controller {

	private static Controller unicaInstancia = null;

	UserRepository userRepo;

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
	}

	public boolean login(String username, String constrasena) {
		User usuario = UserRepository.getInstancia().getUser(username);
		if (usuario == null) {
			return false;
		}
		if(usuario.getContrasena().equals(EncryptDecrypt.encrypt(constrasena))) {
			return false;
		}
		return true;
	}

	public void createUser(String username, String email, String nombreCompleto, Date fechaNacimiento,
			String descripcion, String contrasena) {
		User u = new User(username, email, nombreCompleto, fechaNacimiento, descripcion, contrasena);
		userRepo.createrUser(u);
	}

	public void getAllusers() {
		userRepo.getAllUsers().stream().forEach(u -> System.out.println(u.toString()));
	}

}