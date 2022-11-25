package umu.tds.app.PhotoTDS.model;

import java.util.Date;
import java.util.List;

public class Foto extends Publication {
	private String path;

	public Foto(String titulo, Date fechaPublicacion, String descripcion, int likes,  String path) {
		super(titulo, fechaPublicacion, descripcion, likes);
		this.path = path;
	}
	
	public Foto(String titulo, Date fechaPublicacion, String descripcion,  String path) {
			super(titulo, fechaPublicacion, descripcion);
			this.path = path;
	}
	
	public Foto(String titulo, Date fechaPublicacion, String descripcion, int likes,
			List<HashTag> hashtags, String path) {
		
		super(titulo, fechaPublicacion, descripcion, likes, hashtags);
		this.path = path;
	}

	public Foto(String titulo, Date fechaPublicacion, String descripcion,
			List<HashTag> hashtags, String path) {
		
		this(titulo, fechaPublicacion, descripcion, 0, hashtags, path);
	}
	
	public Foto(String titulo, Date fechaPublicacion, String descripcion, int likes,
			List<HashTag> hashtags, List<Comentario> comentarios, String path) {
		
		super(titulo, fechaPublicacion, descripcion, likes, hashtags, comentarios);
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "Foto [path=" + path + "]";
	}
	
}
