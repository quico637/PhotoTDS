package umu.tds.app.PhotoTDS.persistence;

import umu.tds.app.PhotoTDS.persistence.InterfacesDAO.IUserDAO;

public class DAOFactoryTDS extends DAOFactory{
	public DAOFactoryTDS () {
	}
	
	@Override
	public IUserDAO getUserDAO() {
		return UserAdapterTDS.getInstance();
	}
}
