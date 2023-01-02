package umu.tds.app.PhotoTDS.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author quico y JC
 *
 */
public class Publication {

	private int codigo;
	private String titulo;
	private Date fechaPublicacion;
	private String descripcion;
	private int likes;
	private String creator;
	private List<HashTag> hashtags;	
	private List<Comentario> comentarios;
	
	private static final int MAX_NUM_HASHTAG = 4;
	
	public Publication(String creator, String titulo, Date fechaPublicacion, String descripcion, int likes) {
		super();
		this.creator = creator;
		this.titulo = titulo;
		this.fechaPublicacion = fechaPublicacion;
		this.descripcion = descripcion;
		this.likes = 0;
		this.hashtags = getHashTagsFromText(descripcion);
		this.comentarios = new LinkedList<>();

	}
	
	public Publication(String creator, String titulo, Date fechaPublicacion, String descripcion, int likes,
			List<HashTag> hashtags, List<Comentario> comentarios) {
		super();
		this.creator = creator;
		this.titulo = titulo;
		this.fechaPublicacion = fechaPublicacion;
		this.descripcion = descripcion;
		this.likes = likes;
		this.hashtags = getHashTagsFromText(descripcion);
		this.comentarios = comentarios;

	}
	
	public Publication(String creator, String titulo, Date fechaPublicacion, String descripcion,
			List<HashTag> hashtags, List<Comentario> comentarios) {
		this(creator, titulo, fechaPublicacion, descripcion, 0, hashtags, comentarios);

	}
	
	public Publication(String creator, String titulo, Date fechaPublicacion, String descripcion) {
		this(creator, titulo, fechaPublicacion, descripcion, 0);
	}
	
	
	private List<HashTag> getHashTagsFromText(String text) {
		List<HashTag> lh = new LinkedList<>();
			
		String[] listaPalabras = text.split(" "); 
		for(String palabra : listaPalabras) {
			
			if(lh.size() == MAX_NUM_HASHTAG)
				return lh;
			
			if(Utils.isHashtag(palabra)) {
				HashTag has = HashTag.createHashtag(palabra);
				if(has != null)
					lh.add(has);
			}
		}
		return lh;
	}
	
	
	
	// GETTERS AND SETTERS
	
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getCreator() {
		return this.creator;
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
	

	public List<HashTag> getHashTags() {
		return hashtags;
	}

	public void anadirComentarios(Comentario comentario) {
		this.comentarios.add(comentario);
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "[codigo=" + codigo + ", titulo=" + titulo + ", fechaPublicacion=" + fechaPublicacion
				+ ", descripcion=" + descripcion + ", likes=" + likes + ", hashtags=" + hashtags + ", comentarios="
				+ comentarios + "]";
	}
	
}
