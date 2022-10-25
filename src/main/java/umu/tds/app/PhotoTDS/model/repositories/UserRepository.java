package umu.tds.app.PhotoTDS.model.repositories;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import umu.tds.app.PhotoTDS.model.User;
import umu.tds.app.PhotoTDS.persistence.DAOException;
import umu.tds.app.PhotoTDS.persistence.DAOFactory;
import umu.tds.app.PhotoTDS.persistence.InterfacesDAO.IUserDAO;


public class UserRepository {

	private HashMap<String, User> users;
	private static UserRepository unicaInstancia = null;

	private DAOFactory dao;
	private IUserDAO userAdapter;
	
	public UserRepository() {
		super();
		try {
  			dao = DAOFactory.getInstancia(DAOFactory.DAO_TDS);
  			userAdapter = dao.getUserDAO();
  			users = new HashMap<String,User>();
  			this.cargarCatalogo();
  		} catch (DAOException eDAO) {
  			eDAO.printStackTrace();
  		}
	}

	public static UserRepository getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new UserRepository();
		}

		return unicaInstancia;
	}
	
	public void createrUser(User u) {
		this.userAdapter.createrUser(u);		
	}
	
	private void cargarCatalogo() throws DAOException {
		 List<User> usersDB = userAdapter.readAllUsers();
		 for (User u: usersDB) 
			     users.put(u.getEmail(),u);
	}
		
	public Set<User> getAllUsers() {
		return new LinkedHashSet<>(this.users.values());
	}
	
}