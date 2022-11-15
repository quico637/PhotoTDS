package umu.tds.app.PhotoTDS.model;

import java.util.Date;
import java.util.List;

public class Publication {
	
	private String titulo;
	private Date fechaPublicacion;
	private String descripcion;
	private int likes;
	private List<String> hashtags;
	private List<Comentario> comentarios;
	
	
	
	public Publication(String titulo, Date fechaPublicacion, String descripcion, int likes, List<String> hashtags,
			List<Comentario> comentarios) {
		super();
		this.titulo = titulo;
		this.fechaPublicacion = fechaPublicacion;
		this.descripcion = descripcion;
		this.likes = likes;
		this.hashtags = hashtags;
		this.comentarios = comentarios;
	}
	
	
	public String getTitulo() {
		return titulo;
	}
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public int getLikes() {
		return likes;
	}
	public List<String> getHashtags() {
		return hashtags;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}


	@Override
	public String toString() {
		return "Publication [titulo=" + titulo + ", fechaPublicacion=" + fechaPublicacion + ", descripcion="
				+ descripcion + ", likes=" + likes + ", hashtags=" + hashtags + ", comentarios=" + comentarios + "]";
	}
	
	

}
