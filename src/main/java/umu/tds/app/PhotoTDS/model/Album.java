package umu.tds.app.PhotoTDS.model;

import java.util.List;

public class Album {
	
	private List<Foto> fotos;

	public Album(List<Foto> fotos) {
		super();
		this.fotos = fotos;
	}
	
	public List<Foto> getFotos() {
		return fotos;
	}

	@Override
	public String toString() {
		return "Album [fotos=" + fotos + "]";
	}

}
