package umu.tds.app.PhotoTDS.persistence.InterfacesDAO;

import java.util.List;

import umu.tds.app.PhotoTDS.model.User;

public interface IUserDAO {
	public void createrUser(User u);
	public User readUser(int codigo);
	public void updateUser(User u);
	public void deleteUser(User u);
	public List<User> readAllUsers();
}
