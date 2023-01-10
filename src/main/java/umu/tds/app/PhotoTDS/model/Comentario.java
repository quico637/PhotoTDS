package umu.tds.app.PhotoTDS.model;

import java.util.Date;

/**
 * 
 * @author quico y JC
 *
 */
public class Comentario {

	private int codigo;
	private String texto;
	private Date fechaPublicacion;
	private String autor;

	public Comentario(String texto, Date fechaPublicacion, String autor) {
		super();
		this.texto = texto;
		this.fechaPublicacion = fechaPublicacion;
		this.autor = autor;
	}
	
	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTexto() {
		return texto;
	}


	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}


	public String getAutor() {
		return autor;
	}

	@Override
	public String toString() {
		return "Comentario [codigo=" + codigo + ", texto=" + texto + ", fechaPublicacion=" + fechaPublicacion
				+ ", autorUser=" + autor + "]";
	}

}
