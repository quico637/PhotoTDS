package umu.tds.app.PhotoTDS.model;

public class User {

	private final String nombre;
	private final String email;
	private int codigo;
	
	public User(String nombre, String email) {
		super();
		this.nombre = nombre;
		this.email = email;
	}

	public void setCodigo(int c) {
		this.codigo = c;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}
		
	
	
}