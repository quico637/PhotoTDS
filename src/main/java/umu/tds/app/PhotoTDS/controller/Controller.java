package umu.tds.app.PhotoTDS.controller;

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

	public boolean login() {

		return true;
	}
	
	public void createUser(String nombre, String email) {
		User u = new User(nombre, email);
		userRepo.createrUser(u);	
	}
	
	public void getAllusers() {
		userRepo.getAllUsers().stream().forEach(u -> System.out.println(u.toString()));
	}

}