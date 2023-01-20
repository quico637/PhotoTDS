package umu.tds.app.PhotoTDS.model;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author quico y JC
 *
 */
public class Foto extends Publication {
	private String path;
	

	public Foto(String creator, String titulo, Date fechaPublicacion, String descripcion, int likes,  String path) {
		super(creator, titulo, fechaPublicacion, descripcion, likes);
		this.path = path;
	}
	
	public Foto(String creator, String titulo, Date fechaPublicacion, String descripcion,  String path) {
			super(creator, titulo, fechaPublicacion, descripcion);
			this.path = path;
	}
	
	
	public Foto(String creator, String titulo, Date fechaPublicacion, String descripcion, int likes,
			List<HashTag> hashtags, List<Comentario> comentarios, String path) {
		
		super(creator, titulo, fechaPublicacion, descripcion, likes, hashtags, comentarios);
		this.path = path;
	}
	

	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		return super.toString() + "[Path:" + getPath() + "]";
	}
	
}
