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
//
//		Entidad ePublication = null;
//
//		// Si la entidad esta registrada no la registra de nuevo
//		try {
//			ePublication = servPersistencia.recuperarEntidad(p.getCodigo());
//		} catch (NullPointerException e) {
//		}
//		if (ePublication != null)
//			return;
//
//		// crear entidad Cliente
//		ePublication = new Entidad();
//		ePublication.setNombre("cliente");
//		ePublication.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("email", u.getEmail()),
//				new Propiedad("Publicationname", u.getPublicationname()), new Propiedad("nombreCompleto", u.getNombreCompleto()),
//				new Propiedad("fechaNacimiento", Utils.DateToString(u.getFechaNacimiento())),
//				new Propiedad("descripcion", u.getDescripcion()), new Propiedad("contrasena", u.getContrasena()))));
//
//		// registrar entidad cliente
//		ePublication = servPersistencia.registrarEntidad(ePublication);
//		// asignar identificador unico
//		// Se aprovecha el que genera el servicio de persistencia
//		u.setCodigo(ePublication.getId());

	}

	public Publication readPublication(int codigo) {
//
//		// si no, la recupera de la base de datos
//		Entidad eCliente;
//
//		String email;
//		String Publicationname;
//		String nombreCompleto;
//		String fechaNacimiento;
//		String descripcion;
//		String contrasena;
//
//		// recuperar entidad
//		eCliente = servPersistencia.recuperarEntidad(codigo);
//
//		// recuperar propiedades que no son objetos
//		email = servPersistencia.recuperarPropiedadEntidad(eCliente, "email");
//		Publicationname = servPersistencia.recuperarPropiedadEntidad(eCliente, "Publicationname");
//		nombreCompleto = servPersistencia.recuperarPropiedadEntidad(eCliente, "nombreCompleto");
//		fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eCliente, "fechaNacimiento");
//		descripcion = servPersistencia.recuperarPropiedadEntidad(eCliente, "descripcion");
//		contrasena = servPersistencia.recuperarPropiedadEntidad(eCliente, "contrasena");
//
//		Publication u;
//		u = new Publication(Publicationname, email, nombreCompleto, Utils.StringToDate(fechaNacimiento), descripcion, contrasena);
//		u.setCodigo(codigo);
//		return u;
		return null;
	}

	public void updatePublication(Publication u) {

	}

	public void deletePublication(Publication u) {

	}

	public List<Publication> readAllPublications() {
//		List<Entidad> eClientes = servPersistencia.recuperarEntidades("cliente");
//		List<Publication> clientes = new LinkedList<Publication>();
//
//		for (Entidad eCliente : eClientes) {
//			clientes.add(readPublication(eCliente.getId()));
//		}
//		return clientes;
		return null;
	}

}
