package umu.tds.app.PhotoTDS.model;

import java.util.Date;
import java.util.List;

public class Publication {

	private int codigo;
	private String titulo;
	private Date fechaPublicacion;
	private String descripcion;
	private int likes;
	private List<String> hashtags;
	private List<Comentario> comentarios;
	private String ruta;

	public Publication(String titulo, Date fechaPublicacion, String descripcion, int likes, /*List<String> hashtags,
			List<Comentario> comentarios,*/ String ruta) {
		super();
		this.titulo = titulo;
		this.fechaPublicacion = fechaPublicacion;
		this.descripcion = descripcion;
		this.likes = likes;
		//this.hashtags = hashtags;
		//this.comentarios = comentarios;
		this.ruta = ruta;

	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public List<String> getHashtags() {
		return hashtags;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void anadirComentarios(Comentario comentario) {
		this.comentarios.add(comentario);
	}

	public String getRuta() {
		return ruta;
	}

	@Override
	public String toString() {
		return "Publication [titulo=" + titulo + ", fechaPublicacion=" + fechaPublicacion + ", descripcion="
				+ descripcion + ", likes=" + likes + ", hashtags=" + hashtags + ", comentarios=" + comentarios
				+ ", ruta=" + ruta + "]";
	}

}
