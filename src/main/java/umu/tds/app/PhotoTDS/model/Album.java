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

	public List<Foto> getFotos() {
		return fotos;
	}

	@Override
	public String toString() {
		return "Album [fotos=" + fotos + "]";
	}

}
