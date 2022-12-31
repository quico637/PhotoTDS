package umu.tds.app.PhotoTDS.model.repositories;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import umu.tds.app.PhotoTDS.model.User;
import umu.tds.app.PhotoTDS.persistence.DAOException;
import umu.tds.app.PhotoTDS.persistence.DAOFactory;
import umu.tds.app.PhotoTDS.persistence.IUserDAO;

public class UserRepository {

	private HashMap<String, User> users;
	private static UserRepository unicaInstancia = null;

	private DAOFactory dao;
	private IUserDAO userAdapter;

	private UserRepository() {
		super();
		try {
			dao = DAOFactory.getInstancia(DAOFactory.DAO_TDS);
			userAdapter = dao.getUserDAO();
			users = new HashMap<String, User>();
			this.cargarCatalogo();
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}

	// patron Singletone
	public static UserRepository getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new UserRepository();
		}

		return unicaInstancia;
	}

	public Optional<User> getUser(String username) {

		return Optional.ofNullable(users.get(username));
	}
	

	public List<String> getAllEmails() {
		List<String> l = new LinkedList<>();
		for (String u : this.users.keySet()) {
			// Es un optional
			l.add(this.getUser(u).get().getEmail());
		}
		return l;
	}

	public Optional<User> getUserByEmail(String email) {
		Optional<User> user;
		for (String u : this.users.keySet()) {
			user = getUser(u);
			if (user.isEmpty())
				continue;
			if(user.get().getEmail().equals(email))
				return user;
		}
		return user = Optional.empty();
	}
	
	public Optional<User> getUserFullName(String name) {
		Optional<User> user;
		for (String u : this.users.keySet()) {
			user = getUser(u);
			if (user.isEmpty())
				continue;
			if(user.get().getNombreCompleto().equals(name))
				return user;
		}
		return user = Optional.empty();
	}

	public void createrUser(User u) {
		this.users.put(u.getUsername(), u);
		this.userAdapter.createrUser(u);
	}

	private void cargarCatalogo() throws DAOException {
		List<User> usersDB = userAdapter.readAllUsers();
		for (User u : usersDB)
			users.put(u.getUsername(), u);
	}

	public Set<User> getAllUsers() {
		return new LinkedHashSet<>(this.users.values());
	}

}