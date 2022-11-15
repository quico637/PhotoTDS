package umu.tds.app.PhotoTDS.persistence;

public abstract class DAOFactory {

	private static DAOFactory unicaInstancia;
	
	public static final String DAO_TDS = "umu.tds.app.PhotoTDS.persistence.DAOFactoryTDS";
		
	/** 
	 * Crea un tipo de factoria DAO.
	 * Solo existe el tipo TDSFactoriaDAO
	 */
	public static DAOFactory getInstancia(String tipo) throws DAOException{
		if (unicaInstancia == null)
			try { unicaInstancia=(DAOFactory) Class.forName(tipo).newInstance();
			} catch (Exception e) {	
	  			throw new DAOException(e.getMessage());
			} 
		return unicaInstancia;
	}


	public static DAOFactory getInstancia() throws DAOException{
			if (unicaInstancia == null) return getInstancia (DAOFactory.DAO_TDS);
					else return unicaInstancia;
		}

	/* Constructor */
	protected DAOFactory (){}
		
		
	// Metodos factoria que devuelven adaptadores que implementen estos interfaces
	public abstract IUserDAO getUserDAO();
	
	public abstract IPublicationDAO getPublicationDAO();

	
}

