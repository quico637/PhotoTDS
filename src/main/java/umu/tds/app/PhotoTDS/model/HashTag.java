package umu.tds.app.PhotoTDS.model;


public class HashTag {
	private int codigo;
	private String name;
	
	public HashTag(String name) {
		super();
		this.name = name;
	}
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getName() {
		return name;
	}


	@Override
	public String toString() {
		return "HashTag [codigo=" + codigo + ", name=" + name + "]";
	}
	
}
