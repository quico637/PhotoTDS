package umu.tds.app.PhotoTDS.persistence;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import umu.tds.app.PhotoTDS.model.Utils;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
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

		// crear entidad Cliente
		eUser = new Entidad();
		eUser.setNombre("user");
		eUser.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("email", u.getEmail()),
				new Propiedad("username", u.getUsername()), new Propiedad("nombreCompleto", u.getNombreCompleto()),
				new Propiedad("fechaNacimiento", Utils.DateToString(u.getFechaNacimiento())),
				new Propiedad("descripcion", u.getDescripcion()), new Propiedad("contrasena", u.getContrasena()))));

		// registrar entidad cliente
		eUser = servPersistencia.registrarEntidad(eUser);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		u.setCodigo(eUser.getId());

	}

	public User readUser(int codigo) {

		// si no, la recupera de la base de datos
		Entidad eUser;

		String email;
		String username;
		String nombreCompleto;
		String fechaNacimiento;
		String descripcion;
		String contrasena;

		// recuperar entidad
		eUser = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		email = servPersistencia.recuperarPropiedadEntidad(eUser, "email");
		username = servPersistencia.recuperarPropiedadEntidad(eUser, "username");
		nombreCompleto = servPersistencia.recuperarPropiedadEntidad(eUser, "nombreCompleto");
		fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eUser, "fechaNacimiento");
		descripcion = servPersistencia.recuperarPropiedadEntidad(eUser, "descripcion");
		contrasena = servPersistencia.recuperarPropiedadEntidad(eUser, "contrasena");

		User u;
		u = new User(username, email, nombreCompleto, Utils.StringToDate(fechaNacimiento), descripcion, contrasena);
		u.setCodigo(codigo);
		return u;
	}

	public void updateUser(User u) {
		Entidad ePublication = servPersistencia.recuperarEntidad(u.getCodigo());

		for (Propiedad prop : ePublication.getPropiedades()) {
			if (prop.getNombre().equals("username")) {
				prop.setValor(String.valueOf(u.getUsername()));
			} else if (prop.getNombre().equals("email")) {
				prop.setValor(u.getEmail());
			} else if (prop.getNombre().equals("nombreCompleto")) {
				prop.setValor(u.getNombreCompleto());
			} else if (prop.getNombre().equals("fechaNacimiento")) {
				prop.setValor(Utils.DateToString(u.getFechaNacimiento()));
			}else if (prop.getNombre().equals("descripcion")) {
				prop.setValor(u.getDescripcion());
			}else if (prop.getNombre().equals("contrasena")) {
				prop.setValor(u.getContrasena());
			}
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
}
