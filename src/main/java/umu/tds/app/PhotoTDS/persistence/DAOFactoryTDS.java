package umu.tds.app.PhotoTDS.persistence;

public class DAOFactoryTDS extends DAOFactory {
	public DAOFactoryTDS () {
	}
	
	@Override
	public IUserDAO getUserDAO() {
		return UserAdapterTDS.getInstance();
	}
	
	@Override
	public IPublicationDAO getPublicationDAO() {
		return PublicationAdapterTDS.getInstance();
	}
}
