package umu.tds.app.PhotoTDS.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author quico y JC
 *
 */
public class Album extends Publication {
	
	private List<Foto> fotos;
	private static final int MAX_NUM_PHOTOS = 16;

	public Album(String creator, String titulo, Date fechaPublicacion, String descripcion, int likes, List<Foto> fotos) {
		super(creator, titulo, fechaPublicacion, descripcion, likes);
		this.fotos = fotos;
	}
	
	public Album(String creator, String titulo, Date fechaPublicacion, String descripcion, int likes,
			List<HashTag> hashtags, List<Comentario> comentarios) {
		
		super(creator, titulo, fechaPublicacion, descripcion, likes, hashtags, comentarios);
		this.fotos = new LinkedList<>();
	}
	
	public Album(String creator, String titulo, Date fechaPublicacion, String descripcion, int likes,
			List<HashTag> hashtags, List<Comentario> comentarios, List<Foto> fotos) {
		
		super(creator, titulo, fechaPublicacion, descripcion, likes, hashtags, comentarios);
		this.fotos = fotos;
	}
	
	public Album(String creator, String titulo, Date fechaPublicacion, String descripcion, int likes) {
		super(creator, titulo, fechaPublicacion, descripcion, likes);
		this.fotos = new LinkedList<>();
	}
	
	public Album(String creator, String titulo, Date fechaPublicacion, String descripcion, Foto f) {
		super(creator, titulo, fechaPublicacion, descripcion);
		this.fotos = new LinkedList<>();
		this.fotos.add(f);
		
	}
	
	
	public boolean addFoto(Foto f) {
		if (!this.fotos.contains(f) && this.fotos.size() < MAX_NUM_PHOTOS)
			return false;
		this.fotos.add(f);
		return true;
	}
	
	
	// GETTERS AND SETTERS
	

	public List<Foto> getFotos() {
		return fotos;
	}
	
	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}
	
	public String getPathProfileFoto() {
		return this.fotos.get(0).getPath();
	}
	
	

	@Override
	public String toString() {
		return "Album [fotos=" + fotos + "]";
	}

}
