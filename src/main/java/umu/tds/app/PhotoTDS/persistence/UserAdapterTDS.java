package umu.tds.app.PhotoTDS.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import umu.tds.app.PhotoTDS.model.Utils;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.app.PhotoTDS.model.Publication;
import umu.tds.app.PhotoTDS.model.User;

public class UserAdapterTDS implements IUserDAO {
	private static ServicioPersistencia servPersistencia;
	private static UserAdapterTDS unicaInstancia = null;

	public static IUserDAO getInstance() { // patron singleton
		if (unicaInstancia == null)
			return new UserAdapterTDS();
		else
			return unicaInstancia;
	}

	private UserAdapterTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	public void createrUser(User u) {

		Entidad eUser = null;

		// Si la entidad esta registrada no la registra de nuevo
		try {
			eUser = servPersistencia.recuperarEntidad(u.getCodigo());
		} catch (NullPointerException e) {
		}
		if (eUser != null)
			return;
		
		// registrar primero los atributos que son objetos
		PublicationAdapterTDS adaptadorPub = (PublicationAdapterTDS) PublicationAdapterTDS.getInstance();
		u.getPublications().stream().forEach(p -> adaptadorPub.createPublication(p));
		
		UserAdapterTDS adaptadorUser = (UserAdapterTDS) UserAdapterTDS.getInstance();
		u.getUsuariosSeguidores().stream().forEach(us -> adaptadorUser.createrUser(us));
		u.getUsuariosSeguidos().stream().forEach(us -> adaptadorUser.createrUser(us));
		
		

		// crear entidad Cliente
		eUser = new Entidad();
		eUser.setNombre("user");
		eUser.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad("username", u.getUsername()), 
				new Propiedad("email", u.getEmail()),
				new Propiedad("nombreCompleto", u.getNombreCompleto()),
				new Propiedad("fechaNacimiento", Utils.DateToStringNoHour(u.getFechaNacimiento())),
				new Propiedad("descripcion", u.getDescripcion()),
				new Propiedad("contrasena", u.getContrasena()),
				new Propiedad("profilePic", u.getProfilePic()),
				new Propiedad("premium", String.valueOf(u.isPremium())),
//				new Propiedad("notifications", obtenerCodigosNotificaciones(u.getNotifications())),
				new Propiedad("publications", obtenerCodigosPublications(u.getPublications())),
				new Propiedad("usuariosSeguidores", obtenerCodigosSeguidores(u.getUsuariosSeguidores())),
				new Propiedad("usuariosSeguidos", obtenerCodigosSeguidores(u.getUsuariosSeguidos())),
				new Propiedad("ultimoLogin", Utils.DateToString(u.getUltimoLogin()))
				)));

		// registrar entidad cliente
		eUser = servPersistencia.registrarEntidad(eUser);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		u.setCodigo(eUser.getId());

	}

	public User readUser(int codigo) {
		// Si la entidad esta en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (User) PoolDAO.getUnicaInstancia().getObjeto(codigo);

		// si no, la recupera de la base de datos
		Entidad eUser;

		String username;
		String email;
		String nombreCompleto;
		String fechaNacimiento;
		String descripcion;
		String contrasena;
		String profilePic;
		String premium;
		
//		String notifications;
		String publications;
		String usuariosSeguidores;
		String usuariosSeguidos;
		String ultimoLogin;
		
		
		// recuperar entidad
		eUser = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		username = servPersistencia.recuperarPropiedadEntidad(eUser, "username");
		email = servPersistencia.recuperarPropiedadEntidad(eUser, "email");
		nombreCompleto = servPersistencia.recuperarPropiedadEntidad(eUser, "nombreCompleto");
		fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eUser, "fechaNacimiento");
		descripcion = servPersistencia.recuperarPropiedadEntidad(eUser, "descripcion");
		contrasena = servPersistencia.recuperarPropiedadEntidad(eUser, "contrasena");
		profilePic = servPersistencia.recuperarPropiedadEntidad(eUser, "profilePic");
		premium = servPersistencia.recuperarPropiedadEntidad(eUser, "premium");
		
//		notifications = servPersistencia.recuperarPropiedadEntidad(eUser, "notifications");
		publications = servPersistencia.recuperarPropiedadEntidad(eUser, "publications");
		usuariosSeguidores = servPersistencia.recuperarPropiedadEntidad(eUser, "usuariosSeguidores");
		usuariosSeguidos = servPersistencia.recuperarPropiedadEntidad(eUser, "usuariosSeguidos");
		ultimoLogin = servPersistencia.recuperarPropiedadEntidad(eUser, "ultimoLogin");

		User u;
		u = new User(username, email, nombreCompleto, Utils.StringToDateNoHour(fechaNacimiento), descripcion, contrasena, profilePic
				,Boolean.parseBoolean(premium),
				Utils.StringToDate(ultimoLogin)
				);
		u.setCodigo(codigo);
		
		// IMPORTANTE:aï¿½adir el cliente al pool antes de llamar a otros
		// adaptadores
		PoolDAO.getUnicaInstancia().addObjeto(codigo, u);
		
		// recuperar propiedades que son objetos llamando a adaptadores
		// ventas
		
		u.setPublications(obtenerPublicationsDesdeCodigos(publications));
		u.setUsuariosSeguidores(obtenerSeguidoresDesdeCodigos(usuariosSeguidores));
		u.setUsuariosSeguidos(obtenerSeguidoresDesdeCodigos(usuariosSeguidos));
		
		
		return u;
	}

	public void updateUser(User u) {
		Entidad eUser = servPersistencia.recuperarEntidad(u.getCodigo());

		for (Propiedad prop : eUser.getPropiedades()) {
			if (prop.getNombre().equals("username"))
				prop.setValor(String.valueOf(u.getUsername()));
			else if (prop.getNombre().equals("email")) 
				prop.setValor(u.getEmail());
			else if (prop.getNombre().equals("nombreCompleto")) 
				prop.setValor(u.getNombreCompleto());
			else if (prop.getNombre().equals("fechaNacimiento")) 
				prop.setValor(Utils.DateToStringNoHour(u.getFechaNacimiento()));
			else if (prop.getNombre().equals("descripcion")) 
				prop.setValor(u.getDescripcion());
			else if (prop.getNombre().equals("contrasena")) 
				prop.setValor(u.getContrasena());
			else if(prop.getNombre().equals("profilePic"))
				prop.setValor(u.getProfilePic());
			else if (prop.getNombre().equals("premium"))
				prop.setValor(String.valueOf(u.isPremium()));
			
			
			else if (prop.getNombre().equals("publications")) 
				prop.setValor(obtenerCodigosPublications(u.getPublications()));
			else if (prop.getNombre().equals("usuariosSeguidores"))
				prop.setValor(obtenerCodigosSeguidores(u.getUsuariosSeguidores()));
			else if (prop.getNombre().equals("usuariosSeguidos"))
				prop.setValor(obtenerCodigosSeguidores(u.getUsuariosSeguidos()));
			else if (prop.getNombre().equals("ultimoLogin"))
				prop.setValor(Utils.DateToString(u.getUltimoLogin()));

			servPersistencia.modificarPropiedad(prop);
		}
	}

	public void deleteUser(User u) {
		Entidad eUser = servPersistencia.recuperarEntidad(u.getCodigo());

		servPersistencia.borrarEntidad(eUser);
	}

	public List<User> readAllUsers() {
		List<Entidad> eUser = servPersistencia.recuperarEntidades("user");
		List<User> users = new LinkedList<User>();

		for (Entidad eCliente : eUser) {
			users.add(readUser(eCliente.getId()));
		}
		return users;
	}
	
	// -------------------Funciones auxiliares-----------------------------
	

	// PUBLICATIONS 
	
	
	private String obtenerCodigosPublications(List<Publication> listaPub) {
		String aux = "";
		for (Publication n : listaPub) {
			aux += n.getCodigo() + " ";
		}
		return aux.trim();
	}

	private List<Publication> obtenerPublicationsDesdeCodigos(String pubs) {

		List<Publication> listaPubs = new LinkedList<>();
		StringTokenizer strTok = new StringTokenizer(pubs, " ");
		IPublicationDAO adaptador = PublicationAdapterTDS.getInstance();
		while (strTok.hasMoreTokens()) {
			listaPubs.add(adaptador.readPublication(Integer.valueOf((String) strTok.nextElement())));
		}
		return listaPubs;
	}
	
	
	// USUARIOS SEGUIDORES 
	
	private String obtenerCodigosSeguidores(List<User> l) {
		String aux = "";
		for (User n : l) {
			aux += n.getCodigo() + " ";
		}
		return aux.trim();
	}

	private List<User> obtenerSeguidoresDesdeCodigos(String segs) {

		List<User> l = new LinkedList<>();
		StringTokenizer strTok = new StringTokenizer(segs, " ");
		UserAdapterTDS adaptador = (UserAdapterTDS) UserAdapterTDS.getInstance();
		while (strTok.hasMoreTokens()) {
			l.add(adaptador.readUser(Integer.valueOf((String) strTok.nextElement())));
		}
		return l;
	}

	
}
