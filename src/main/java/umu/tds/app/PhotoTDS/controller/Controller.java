package umu.tds.app.PhotoTDS.controller;

import umu.tds.app.PhotoTDS.model.User;
import umu.tds.app.PhotoTDS.model.repositories.UserRepository;

public class Controller {

	// Estoy en la rama controller

	private static Controller unicaInstancia = null;

	private Controller() {
			
		}

	public static Controller getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new Controller();
		}

		return unicaInstancia;
	}

	public boolean login() {

		return true;
	}
	
	public void createUser(String nombre, String email) {
		UserRepository.getInstancia().createrUser(new User(nombre, email));
	}
	
	public void getAllusers() {
		UserRepository.getInstancia().getAllUsers().stream().forEach(u -> System.out.println(u));
	}

}