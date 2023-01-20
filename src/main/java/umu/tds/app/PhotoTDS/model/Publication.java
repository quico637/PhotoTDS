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
		this.likes = likes;
		this.hashtags = getHashTagsFromText(descripcion);
		this.comentarios = new LinkedList<>();

	}
	
	public Publication(String creator, String titulo, Date fechaPublicacion, String descripcion, int likes,
			List<HashTag> hashtags, List<Comentario> comentarios) {
		this(creator, titulo, fechaPublicacion, descripcion);
		this.hashtags = hashtags;
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
	
	public List<HashTag> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<HashTag> hashtags) {
		this.hashtags = hashtags;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
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

	
	public List<Comentario> getComentarios() {
		return new LinkedList<>(comentarios);
	}
	

	public List<HashTag> getHashTags() {
		return new LinkedList<>(hashtags);
	}

	public Comentario anadirComentarios(String comentario, String user) {
		Comentario coment = new Comentario(comentario, new Date(), user);
		this.comentarios.add(coment);
		return coment;
	}
	
	public void addMeGusta() {
		this.likes = this.likes + 1;
	}
	
}
