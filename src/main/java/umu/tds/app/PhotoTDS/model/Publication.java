package umu.tds.app.PhotoTDS.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Publication {

	private int codigo;
	private String titulo;
	private Date fechaPublicacion;
	private String descripcion;
	private int likes;
	private List<Comentario> comentarios;
	
	public Publication(String titulo, Date fechaPublicacion, String descripcion, int likes) {
		super();
		this.titulo = titulo;
		this.fechaPublicacion = fechaPublicacion;
		this.descripcion = descripcion;
		this.likes = likes;
		this.comentarios = new LinkedList<>();

	}

	public Publication(String titulo, Date fechaPublicacion, String descripcion, int likes,
			List<Comentario> comentarios) {
		super();
		this.titulo = titulo;
		this.fechaPublicacion = fechaPublicacion;
		this.descripcion = descripcion;
		this.likes = likes;
		this.comentarios = comentarios;

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
	
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void anadirComentarios(Comentario comentario) {
		this.comentarios.add(comentario);
	}

	@Override
	public String toString() {
		return "Publication [titulo=" + titulo + ", fechaPublicacion=" + fechaPublicacion + ", descripcion="
				+ descripcion + ", likes=" + likes + ", comentarios=" + comentarios + "]";
	}

}
