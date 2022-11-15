package umu.tds.app.PhotoTDS.persistence;

import java.util.List;

import umu.tds.app.PhotoTDS.model.Publication;

public interface IPublicationDAO {
	
	public void createPublication(Publication u);
	public Publication readPublication(int codigo);
	public void updatePublication(Publication u);
	public void deletePublication(Publication u);
	public List<Publication> readAllPublications();
}
