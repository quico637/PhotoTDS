package umu.tds.app.PhotoTDS.model;

/**
 * 
 * @author quico y JC
 *
 */
public class HashTag {
	private int codigo;
	private String name;
	private static final int MAX_HASTAG_LENGTH = 15;
	
	private HashTag(String name) {
		super();
		this.name = name;
	}
	
	public static HashTag createHashtag(String name) {
		if(name.length() <= MAX_HASTAG_LENGTH) {
			return new HashTag(name);
		}
		return null;
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
