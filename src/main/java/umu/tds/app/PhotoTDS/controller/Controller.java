package umu.tds.app.PhotoTDS.controller;

import umu.tds.app.PhotoTDS.model.User;
import umu.tds.app.PhotoTDS.model.repositories.UserRepository;
import umu.tds.app.PhotoTDS.persistence.DAOFactory;
import umu.tds.app.PhotoTDS.persistence.IUserDAO;
import umu.tds.app.PhotoTDS.persistence.DAOException;

public class Controller {

	private static Controller unicaInstancia = null;
	
	UserRepository userRepo;
	IUserDAO userAdapter;

	private Controller() {
			inicializarCatalogos();
			inicializarAdaptadores();
		}

	public static Controller getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new Controller();
		}
		
		return unicaInstancia;
	}
	
	private void inicializarAdaptadores() {
		DAOFactory factoria = null;
		try {
			factoria = DAOFactory.getInstancia(DAOFactory.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		userAdapter = factoria.getUserDAO();
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