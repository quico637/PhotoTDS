package umu.tds.app.PhotoTDS.model.repositories;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import umu.tds.app.PhotoTDS.model.Publication;
import umu.tds.app.PhotoTDS.persistence.DAOException;
import umu.tds.app.PhotoTDS.persistence.DAOFactory;
import umu.tds.app.PhotoTDS.persistence.IPublicationDAO;

public class PublicationRepository {

	private HashMap<String, Publication> publications;
	private static PublicationRepository unicaInstancia = null;

	private DAOFactory dao;
	private IPublicationDAO publicationAdapter;

	private PublicationRepository() {
		super();
		try {
			dao = DAOFactory.getInstancia(DAOFactory.DAO_TDS);
			publicationAdapter = dao.getPublicationDAO();
			publications = new HashMap<String, Publication>();
			this.cargarCatalogo();
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}

	// patron Singletone
	public static PublicationRepository getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new PublicationRepository();
		}

		return unicaInstancia;
	}

	public Publication getPublication(String title) {
		if (publications.containsKey(title)) {
			return publications.get(title);
		}
		return null;
	}

	public void createPublication(Publication p) {
		this.publications.put(p.getTitulo(), p);
		this.publicationAdapter.createPublication(p);
	}

	private void cargarCatalogo() throws DAOException {
		List<Publication> publicationDB = publicationAdapter.readAllPublications();
		for (Publication p : publicationDB)
			publications.put(p.getTitulo(), p);
	}

	public Set<Publication> getAllPublications() {
		return new LinkedHashSet<>(this.publications.values());
	}

}
