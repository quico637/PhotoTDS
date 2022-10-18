package umu.tds.app.PhotoTDS.controller;

import umu.tds.app.PhotoTDS.model.repositories.UserRepository;

public class Controller {

	// Estoy en la rama controller

	private static Controller unicaInstancia = null;

	private Controller() {
			;
		}

	public static Controller getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new Controller();
		}

		return unicaInstancia;
	}

	public boolean login() {
		System.out.println(UserRepository.getInstancia().getUsers());
		return true;
	}

}