package umu.tds.app.PhotoTDS.model.repositories;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import umu.tds.app.PhotoTDS.model.HashTag;
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

	public Optional<Publication> getPublication(String title) {
		return Optional.ofNullable(publications.get(title));
	}

	public void createPublication(Publication p) {
		this.publications.put(p.getTitulo(), p);
		this.publicationAdapter.createPublication(p);
	}
	
	public void updatePublication(Publication p) {
		this.publicationAdapter.updatePublication(p);
	}
	
	public void removePublication(Publication p) {
		this.publications.remove(p.getTitulo());
		this.publicationAdapter.deletePublication(p);
	}
	
	public List<Publication> getPublicationsFromHtg(HashTag hashtag) {
		List<Publication> l = new LinkedList<>();
		for(Publication p : this.publications.values()) {
			if(p.getHashTags().stream().anyMatch(h -> h.getName().equals(hashtag.getName())))
				l.add(p);
		}
		return l;
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
