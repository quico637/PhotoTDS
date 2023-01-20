package umu.tds.app.PhotoTDS.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.app.PhotoTDS.model.Album;
import umu.tds.app.PhotoTDS.model.Comentario;
import umu.tds.app.PhotoTDS.model.Foto;
import umu.tds.app.PhotoTDS.model.Publication;
import umu.tds.app.PhotoTDS.model.HashTag;
import umu.tds.app.PhotoTDS.model.Utils;

public class PublicationAdapterTDS implements IPublicationDAO {
	private static ServicioPersistencia servPersistencia;
	private static PublicationAdapterTDS unicaInstancia = null;

	public static IPublicationDAO getInstance() { // patron singleton
		if (unicaInstancia == null)
			return new PublicationAdapterTDS();
		else
			return unicaInstancia;
	}

	private PublicationAdapterTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	public void createPublication(Publication p) {

		Entidad ePublication = null;

		// Si la entidad esta registrada no la registra de nuevo
		try {
			ePublication = servPersistencia.recuperarEntidad(p.getCodigo());
		} catch (NullPointerException e) {
		}
		if (ePublication != null)
			return;

		// registrar primero los atributos que son objetos
		HashTagAdapterTDS adaptadorHashtag = (HashTagAdapterTDS) HashTagAdapterTDS.getInstance();
		p.getHashTags().stream().forEach(h -> adaptadorHashtag.createHashTag(h));
		
		ComentarioAdapterTDS adaptadorCom = (ComentarioAdapterTDS) ComentarioAdapterTDS.getInstance();
		p.getComentarios().stream().forEach(c -> adaptadorCom.createComentario(c));
		
	
		



		// crear entidad Cliente
		ePublication = new Entidad();
		if (p instanceof Foto) {
			ePublication.setNombre("photo");
			ePublication.setPropiedades(new ArrayList<Propiedad>(
					Arrays.asList(new Propiedad("creator", p.getCreator()), 
							new Propiedad("titulo", p.getTitulo()),
							new Propiedad("fechaPublicacion", Utils.DateToString(p.getFechaPublicacion())),
							new Propiedad("descripcion", p.getDescripcion()),
							new Propiedad("likes", String.valueOf(p.getLikes())),
							new Propiedad("hashtags", obtenerCodigosHashTags(p.getHashTags())),
							new Propiedad("comentarios", obtenerCodigosComentarios(p.getComentarios())),
							new Propiedad("path", ((Foto) p).getPath()))));

			System.out.println("estoy en pub adapter()");
			
		} else if (p instanceof Album) {
			
			((Album)p).getFotos().stream().forEach(f -> getInstance().createPublication(f));
			
			ePublication.setNombre("album");
			ePublication.setPropiedades(new ArrayList<Propiedad>(
					Arrays.asList(new Propiedad("creator", p.getCreator()), 
							new Propiedad("titulo", p.getTitulo()),
							new Propiedad("fechaPublicacion", Utils.DateToString(p.getFechaPublicacion())),
							new Propiedad("descripcion", p.getDescripcion()),
							new Propiedad("likes", String.valueOf(p.getLikes())),
							new Propiedad("hashtags", obtenerCodigosHashTags(p.getHashTags())),
							new Propiedad("comentarios", obtenerCodigosComentarios(p.getComentarios())),
							new Propiedad("fotos", obtenerCodigosFotos(((Album) p).getFotos())))));
		}

		// registrar entidad cliente
		ePublication = servPersistencia.registrarEntidad(ePublication);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		p.setCodigo(ePublication.getId());
		System.out.println("amo a ver ?????? ---- " + p.getCodigo());
	}

	public Publication readPublication(int codigo) {

		// Si la entidad esta en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(codigo)) {
			Object o = PoolDAO.getUnicaInstancia().getObjeto(codigo);
			if( o instanceof Foto)
				return (Foto) o;
			else if (o instanceof Album)
				return (Album) o;
		}
			
			
		Entidad ePublication;
		System.out.println("eCodigo = " + codigo);
		ePublication = servPersistencia.recuperarEntidad(codigo);
		if (ePublication.getNombre().equals("photo"))
			return readFoto(codigo);
		else if (ePublication.getNombre().equals("album"))
			return readAlbum(codigo);
		else
			return null;
	}

	private Foto readFoto(int codigo) {

//		if (PoolDAO.getUnicaInstancia().contiene(codigo))
//			return (Foto) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		// si no, la recupera de la base de datos
		Entidad ePublication;

		String creator;
		String titulo;
		String fechaPublicacion;
		String descripcion;
		String likes;

		String hashtags;
		String comentarios;

		String path;

		// recuperar entidad
		ePublication = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		creator = servPersistencia.recuperarPropiedadEntidad(ePublication, "creator");
		titulo = servPersistencia.recuperarPropiedadEntidad(ePublication, "titulo");
		fechaPublicacion = servPersistencia.recuperarPropiedadEntidad(ePublication, "fechaPublicacion");
		descripcion = servPersistencia.recuperarPropiedadEntidad(ePublication, "descripcion");
		likes = servPersistencia.recuperarPropiedadEntidad(ePublication, "likes");
		hashtags = servPersistencia.recuperarPropiedadEntidad(ePublication, "hashtags");
		comentarios = servPersistencia.recuperarPropiedadEntidad(ePublication, "comentarios");
	
		path = servPersistencia.recuperarPropiedadEntidad(ePublication, "path");

		Foto p;
		p = new Foto(creator, titulo, Utils.StringToDate(fechaPublicacion), descripcion, Integer.parseInt(likes), path);
		p.setCodigo(codigo);

		PoolDAO.getUnicaInstancia().addObjeto(codigo, p);
		
		System.out.println("CoMENTARIOS: " + comentarios);
		p.setHashtags(obtenerHashTagsDesdeCodigos(hashtags)); 
		p.setComentarios(obtenerComentariosDesdeCodigos(comentarios));

		return p;
	}

	private Album readAlbum(int codigo) {

//		if (PoolDAO.getUnicaInstancia().contiene(codigo))
//			return (Album) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		// si no, la recupera de la base de datos
		Entidad ePublication;

		String creator;
		String titulo;
		String fechaPublicacion;
		String descripcion;
		String likes;

		String hashtags;
		String comentarios;

		String fotos;

		// recuperar entidad
		ePublication = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		creator = servPersistencia.recuperarPropiedadEntidad(ePublication, "creator");
		titulo = servPersistencia.recuperarPropiedadEntidad(ePublication, "titulo");
		fechaPublicacion = servPersistencia.recuperarPropiedadEntidad(ePublication, "fechaPublicacion");
		descripcion = servPersistencia.recuperarPropiedadEntidad(ePublication, "descripcion");
		likes = servPersistencia.recuperarPropiedadEntidad(ePublication, "likes");
		hashtags = servPersistencia.recuperarPropiedadEntidad(ePublication, "hashtags");
		comentarios = servPersistencia.recuperarPropiedadEntidad(ePublication, "comentarios");
		fotos = servPersistencia.recuperarPropiedadEntidad(ePublication, "fotos");

		Album p;
		p = new Album(creator, titulo, Utils.StringToDate(fechaPublicacion), descripcion, Integer.parseInt(likes));
		p.setCodigo(codigo);

		PoolDAO.getUnicaInstancia().addObjeto(codigo, p);
		
		p.setHashtags(obtenerHashTagsDesdeCodigos(hashtags)); 
		p.setComentarios(obtenerComentariosDesdeCodigos(comentarios));
		((Album)p).setFotos(obtenerFotosDesdeCodigos(fotos));

		return p;
	}

	public void updatePublication(Publication p) {
		Entidad ePublication = servPersistencia.recuperarEntidad(p.getCodigo());

		if (ePublication.getNombre().equals("photo"))
			updateFoto((Foto) p);
		else if (ePublication.getNombre().equals("album"))
			updateAlbum((Album) p);

	}

	private void updateFoto(Foto p) {
		Entidad ePublication = servPersistencia.recuperarEntidad(p.getCodigo());

		for (Propiedad prop : ePublication.getPropiedades()) {
			if (prop.getNombre().equals("titulo")) {
				prop.setValor(String.valueOf(p.getTitulo()));
			} else if (prop.getNombre().equals("creator")) {
				prop.setValor(p.getCreator());
			} else if (prop.getNombre().equals("fechaPublicacion")) {
				prop.setValor(Utils.DateToString(p.getFechaPublicacion()));
			} else if (prop.getNombre().equals("descripcion")) {
				prop.setValor(p.getDescripcion());
			} else if (prop.getNombre().equals("likes")) {
				prop.setValor(String.valueOf(p.getLikes()));
			} else if (prop.getNombre().equals("hashtags")) {
				prop.setValor(obtenerCodigosHashTags(p.getHashTags()));
			} else if (prop.getNombre().equals("comentarios")) {
				
				ComentarioAdapterTDS adaptadorCom = (ComentarioAdapterTDS) ComentarioAdapterTDS.getInstance();
				p.getComentarios().stream().forEach(c -> adaptadorCom.createComentario(c));
				
				prop.setValor(obtenerCodigosComentarios(p.getComentarios()));
			} else if (prop.getNombre().equals("path")) {
				prop.setValor(p.getPath());
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	private void updateAlbum(Album p) {
		Entidad ePublication = servPersistencia.recuperarEntidad(p.getCodigo());

		for (Propiedad prop : ePublication.getPropiedades()) {
			if (prop.getNombre().equals("titulo")) {
				prop.setValor(String.valueOf(p.getTitulo()));
			} else if (prop.getNombre().equals("creator")) {
				prop.setValor(p.getCreator());
			} else if (prop.getNombre().equals("fechaPublicacion")) {
				prop.setValor(Utils.DateToString(p.getFechaPublicacion()));
			} else if (prop.getNombre().equals("descripcion")) {
				prop.setValor(p.getDescripcion());
			} else if (prop.getNombre().equals("likes")) {
				prop.setValor(String.valueOf(p.getLikes()));
			} else if (prop.getNombre().equals("hashtags")) {
				prop.setValor(obtenerCodigosHashTags(p.getHashTags()));
			} else if (prop.getNombre().equals("comentarios")) {
				

				ComentarioAdapterTDS adaptadorCom = (ComentarioAdapterTDS) ComentarioAdapterTDS.getInstance();
				p.getComentarios().stream().forEach(c -> adaptadorCom.createComentario(c));
				
				prop.setValor(obtenerCodigosComentarios(p.getComentarios()));
			} else if (prop.getNombre().equals("fotos")) {
				prop.setValor(obtenerCodigosFotos(p.getFotos()));
			}
			servPersistencia.modificarPropiedad(prop);
		}

	}

	public void deletePublication(Publication p) {
		Entidad ePublication = servPersistencia.recuperarEntidad(p.getCodigo());

		servPersistencia.borrarEntidad(ePublication);
	}

	public List<Publication> readAllPublications() {
		List<Entidad> ePhoto = servPersistencia.recuperarEntidades("photo");
		List<Entidad> eAlbum = servPersistencia.recuperarEntidades("album");
		List<Entidad> ePublication = new LinkedList<>(ePhoto);
		ePublication.addAll(eAlbum);

		List<Publication> publicaciones = new LinkedList<>();

		for (Entidad eCliente : ePublication) {
			publicaciones.add(readPublication(eCliente.getId()));
			System.out.println("readed pub " + eCliente.getId());
		}
		return publicaciones;
	}

	// -------------------Funciones auxiliares-----------------------------

	// NOTIFICATIONS

	private String obtenerCodigosComentarios(List<Comentario> listaCom) {
		String aux = "";
		for (Comentario n : listaCom) {
			aux += n.getCodigo() + " ";
		}
		return aux.trim();
	}

	private List<Comentario> obtenerComentariosDesdeCodigos(String coms) {

		List<Comentario> l = new LinkedList<>();
		StringTokenizer strTok = new StringTokenizer(coms, " ");
		IComentarioDAO adaptador = ComentarioAdapterTDS.getInstance();
		while (strTok.hasMoreTokens()) {
			l.add(adaptador.readComentario(Integer.valueOf((String) strTok.nextElement())));
		}
		return l;
	}

	// HASHTAGS

	private String obtenerCodigosHashTags(List<HashTag> listaH) {
		String aux = "";
		for (HashTag n : listaH) {
			aux += n.getCodigo() + " ";
		}
		return aux.trim();
	}

	private List<HashTag> obtenerHashTagsDesdeCodigos(String hashtags) {

		List<HashTag> l = new LinkedList<>();
		StringTokenizer strTok = new StringTokenizer(hashtags, " ");
		IHashTagDAO adaptador = HashTagAdapterTDS.getInstance();
		while (strTok.hasMoreTokens()) {
			l.add(adaptador.readHashTag(Integer.valueOf((String) strTok.nextElement())));
		}
		return l;
	}

	// FOTOS

	private String obtenerCodigosFotos(List<Foto> listaFotos) {
		String aux = "";
		for (Foto n : listaFotos) {
			aux += n.getCodigo() + " ";
		}
		return aux.trim();
	}

	private List<Foto> obtenerFotosDesdeCodigos(String coms) {

		List<Foto> l = new LinkedList<>();
		StringTokenizer strTok = new StringTokenizer(coms, " ");
		while (strTok.hasMoreTokens()) {
			l.add(readFoto(Integer.valueOf((String) strTok.nextElement())));
		}
		return l;
	}
}
