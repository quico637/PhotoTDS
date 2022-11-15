package umu.tds.app.PhotoTDS.model;

public class Foto {
	
	private String path;

	public Foto(String path) {
		super();
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
