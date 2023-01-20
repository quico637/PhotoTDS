package umu.tds.app.PhotoTDS.model.repositories;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import umu.tds.app.PhotoTDS.model.Album;
import umu.tds.app.PhotoTDS.model.Foto;
import umu.tds.app.PhotoTDS.model.HashTag;
import umu.tds.app.PhotoTDS.model.Publication;
import umu.tds.app.PhotoTDS.model.User;
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
		
		if(p instanceof Album) {
			for(Foto f : ((Album)p).getFotos()) {
				removePublication(f);
			}
		}
		this.publications.remove(p.getTitulo());
		this.publicationAdapter.deletePublication(p);
	}
	
	public List<Object> getPublicationsFromHtg(List<String> hashtags) {
						
		System.out.println("all hashtags ///: " + hashtags);
		return this.publications.values().stream()
			.filter(p -> p.getHashtags().stream()
							.map(HashTag::getName)
							.collect(Collectors.toList()).containsAll(hashtags))
			.map(p -> (Object) p)
			.collect(Collectors.toList());
					
	}

	private void cargarCatalogo() throws DAOException {
		List<Publication> publicationDB = publicationAdapter.readAllPublications();
		for (Publication p : publicationDB)
			publications.put(p.getTitulo(), p);
	}

	public Set<Publication> getAllPublications() {
		return new LinkedHashSet<>(this.publications.values());
	}
	
	public List<Publication> getAllPublicationsUser(String u) {
		return this.getAllPublications().stream()
			.filter(p -> p.getCreator().equals(u))
			.collect(Collectors.toList());
	}
	
	public List<Publication> getPublicationsFromFollowers(User u) {
		return this.publications.values().stream()
				.filter(p -> u.getUsuariosSeguidos().contains(UserRepository.getInstancia().getUser(p.getCreator()).get()))
				.collect(Collectors.toList());
	}

}
