package umu.tds.app.PhotoTDS.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.app.PhotoTDS.model.User;
import umu.tds.app.PhotoTDS.persistence.InterfacesDAO.IUserDAO;

public class UserAdapterTDS implements IUserDAO{
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
		} catch (NullPointerException e) {}
		if (eUser != null) return;
		
		// crear entidad Cliente
		eUser = new Entidad();
		eUser.setNombre("cliente");
		eUser.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("email", u.getEmail()), 
								new Propiedad("nombre", u.getNombre()))));

		// registrar entidad cliente
		eUser = servPersistencia.registrarEntidad(eUser);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		u.setCodigo(eUser.getId());
		
	}
	
	public User readUser(int codigo) {
		
		// si no, la recupera de la base de datos
		Entidad eCliente;
		
		String email;
		String nombre;

		// recuperar entidad
		eCliente = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		email = servPersistencia.recuperarPropiedadEntidad(eCliente, "email");
		nombre = servPersistencia.recuperarPropiedadEntidad(eCliente, "nombre");

		User u = new User(nombre, email);
		u.setCodigo(codigo);

		return u;
	}
	public void updateUser(User u) {
		
	}
	public void deleteUser(User u) {
		
	}
	public List<User> readAllUsers() {
		List<Entidad> eClientes = servPersistencia.recuperarEntidades("cliente");
		List<User> clientes = new LinkedList<User>();

		for (Entidad eCliente : eClientes) {
			clientes.add(readUser(eCliente.getId()));
		}
		return clientes;
	}
}
