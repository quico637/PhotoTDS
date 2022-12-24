package umu.tds.app.PhotoTDS.model;

import java.util.Date;

/**
 * 
 * @author quico y JC
 *
 */
public class Notification {
	
	private int codigo;
	private Date date;
	private String text;

	public Notification(Date date, String text) {
		super();
		this.date = date;
		this.text = text;
	}

	public int getCodigo() {
		return codigo;
	}

	public Date getDate() {
		return date;
	}

	public String getText() {
		return text;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Notification [codigo=" + codigo + ", date=" + date + ", text=" + text + "]";
	}


}
