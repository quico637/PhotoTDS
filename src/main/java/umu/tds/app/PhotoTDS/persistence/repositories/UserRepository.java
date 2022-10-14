package umu.tds.app.PhotoTDS.persistence.repositories;

import java.util.LinkedList;
import java.util.List;

import umu.tds.app.PhotoTDS.model.User;


public class UserRepository {

	private List<User> users;
	private static UserRepository unicaInstancia = null;

	public UserRepository() {
		super();
		this.users = new LinkedList<>();
		this.users.add(new User("penco", "quico@mgail.com"));
	}
	
	public static UserRepository getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new UserRepository();
		}

		return unicaInstancia;
	}

	public List<User> getUsers() {
		// aliasing
		return users;
	}

	
}