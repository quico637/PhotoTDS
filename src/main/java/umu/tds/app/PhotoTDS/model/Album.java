package umu.tds.app.PhotoTDS.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Album extends Publication {
	
	private List<Foto> fotos;

	public Album(String titulo, Date fechaPublicacion, String descripcion, int likes, List<Foto> fotos) {
		super(titulo, fechaPublicacion, descripcion, likes);
		this.fotos = fotos;
	}
	
	public Album(String titulo, Date fechaPublicacion, String descripcion, int likes,
			List<HashTag> hashtags, List<Comentario> comentarios) {
		
		super(titulo, fechaPublicacion, descripcion, likes, hashtags, comentarios);
		this.fotos = new LinkedList<>();
	}
	
	public Album(String titulo, Date fechaPublicacion, String descripcion, int likes,
			List<HashTag> hashtags, List<Comentario> comentarios, List<Foto> fotos) {
		
		super(titulo, fechaPublicacion, descripcion, likes, hashtags, comentarios);
		this.fotos = fotos;
	}
	
	public Album(String titulo, Date fechaPublicacion, String descripcion, int likes) {
		super(titulo, fechaPublicacion, descripcion, likes);
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