package umu.tds.app.PhotoTDS.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.app.PhotoTDS.model.Publication;
import umu.tds.app.PhotoTDS.model.Utils;

public class PublicationAdapterTDS implements IPublicationDAO{
	
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

		// crear entidad Cliente
		ePublication = new Entidad();
		ePublication.setNombre("publication");
		ePublication.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad("titulo", p.getTitulo()),
				new Propiedad("fechaPublicacion", Utils.DateToString(p.getFechaPublicacion())), 
				new Propiedad("descripcion", p.getDescripcion()),
				new Propiedad("likes", String.valueOf(p.getLikes())), 
				/*new Propiedad("hashtags", p.getHashtags()), */
				/*new Propiedad("comentarios", p.getComentarios())*/
				new Propiedad("ruta", p.getRuta()))));

		// registrar entidad cliente
		ePublication = servPersistencia.registrarEntidad(ePublication);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		p.setCodigo(ePublication.getId());

	}

	public Publication readPublication(int codigo) {

		// si no, la recupera de la base de datos
		Entidad ePublication;

		String titulo;
		String fechaPublicacion;
		String descripcion;
		String likes;
		//String hashtags;
		//String comentarios;
		String ruta;

		// recuperar entidad
		ePublication = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		titulo = servPersistencia.recuperarPropiedadEntidad(ePublication, "titulo");
		fechaPublicacion = servPersistencia.recuperarPropiedadEntidad(ePublication, "fechaPublicacion");
		descripcion = servPersistencia.recuperarPropiedadEntidad(ePublication, "descripcion");
		likes = servPersistencia.recuperarPropiedadEntidad(ePublication, "likes");
		//hashtags = servPersistencia.recuperarPropiedadEntidad(ePublication, "hashtags");
		//comentarios = servPersistencia.recuperarPropiedadEntidad(ePublication, "comentarios");
		ruta = servPersistencia.recuperarPropiedadEntidad(ePublication, "ruta");

		Publication p;
		p = new Publication(titulo, Utils.StringToDate(fechaPublicacion), descripcion, Integer.parseInt(likes), /*hashtags, comentarios*/ ruta);
		p.setCodigo(codigo);
		return p;
	}

	public void updatePublication(Publication p) {
		Entidad ePublication = servPersistencia.recuperarEntidad(p.getCodigo());

		for (Propiedad prop : ePublication.getPropiedades()) {
			if (prop.getNombre().equals("titulo")) {
				prop.setValor(String.valueOf(p.getTitulo()));
			} else if (prop.getNombre().equals("fechaPublicacion")) {
				prop.setValor(Utils.DateToString(p.getFechaPublicacion()));
			} else if (prop.getNombre().equals("descripcion")) {
				prop.setValor(p.getDescripcion());
			} /*else if (prop.getNombre().equals("hashtags")) {
				prop.setValor(p.gethashtags());
			}else if (prop.getNombre().equals("comentarios")) {
				prop.setValor(p.getComentarios());
			}*/else if (prop.getNombre().equals("ruta")) {
				prop.setValor(p.getRuta());
			}
			servPersistencia.modificarPropiedad(prop);
		}

	}

	public void deletePublication(Publication p) {
		Entidad ePublication = servPersistencia.recuperarEntidad(p.getCodigo());

		servPersistencia.borrarEntidad(ePublication);
	}

	public List<Publication> readAllPublications() {
		List<Entidad> ePublication = servPersistencia.recuperarEntidades("publication");
		List<Publication> publicaciones = new LinkedList<Publication>();

		for (Entidad eCliente : ePublication) {
			publicaciones.add(readPublication(eCliente.getId()));
		}
		return publicaciones;
	}

}
