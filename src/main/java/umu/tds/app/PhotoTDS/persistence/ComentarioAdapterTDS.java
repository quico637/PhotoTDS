package umu.tds.app.PhotoTDS.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.app.PhotoTDS.model.Comentario;
import umu.tds.app.PhotoTDS.model.Utils;

public class ComentarioAdapterTDS implements IComentarioDAO {
	private static ServicioPersistencia servPersistencia;
	private static ComentarioAdapterTDS unicaInstancia = null;

	public static IComentarioDAO getInstance() { // patron singleton
		if (unicaInstancia == null)
			return new ComentarioAdapterTDS();
		else
			return unicaInstancia;
	}

	private ComentarioAdapterTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public void createComentario(Comentario c) {
		Entidad eComentario = null;

		// Si la entidad esta registrada no la registra de nuevo
		try {
			eComentario = servPersistencia.recuperarEntidad(c.getCodigo());
		} catch (NullPointerException e) {
			
		}
		if (eComentario != null)
			return;

		// crear entidad Cliente
		eComentario = new Entidad();
		eComentario.setNombre("comentario");
		eComentario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad("texto", c.getTexto()),
				new Propiedad("fechaPublicacion", Utils.DateToString(c.getFechaPublicacion())),
				new Propiedad("autor", String.valueOf(c.getAutor().getCodigo()))
				)));

		// registrar entidad cliente
		eComentario = servPersistencia.registrarEntidad(eComentario);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		c.setCodigo(eComentario.getId());
		
	}
	public Comentario readComentario(int codigo) {
		// si no, la recupera de la base de datos
		Entidad eComentario;

		String texto;
		String fechaPublicacion;
		String autor;
		// recuperar entidad
		eComentario = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		texto = servPersistencia.recuperarPropiedadEntidad(eComentario, "texto");
		fechaPublicacion = servPersistencia.recuperarPropiedadEntidad(eComentario, "fechaPublicacion");
		autor = servPersistencia.recuperarPropiedadEntidad(eComentario, "autor");
		Comentario c;
		c = new Comentario(texto, Utils.StringToDate(fechaPublicacion), UserAdapterTDS.getInstance().readUser(Integer.parseInt(autor)));
		c.setCodigo(codigo);
		return c;
	}
	public void updateComentario(Comentario c) {
		Entidad eComentario = servPersistencia.recuperarEntidad(c.getCodigo());

		for (Propiedad prop : eComentario.getPropiedades()) {
			if (prop.getNombre().equals("texto")) {
				prop.setValor(c.getTexto());
			} else if (prop.getNombre().equals("fechaPublicacion")) {
				prop.setValor(Utils.DateToString(c.getFechaPublicacion()));
			}
			else if (prop.getNombre().equals("autor")) {
				prop.setValor(String.valueOf(c.getAutor()));
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}
	public void deleteComentario(Comentario c) {
		Entidad eComentario = servPersistencia.recuperarEntidad(c.getCodigo());

		servPersistencia.borrarEntidad(eComentario);
	}
	public List<Comentario> readAllComentarios() {
		List<Entidad> eComentario = servPersistencia.recuperarEntidades("comentario");
		List<Comentario> comentarios = new LinkedList<>();

		for (Entidad eComment : eComentario) {
			comentarios.add(readComentario(eComment.getId()));
		}
		return comentarios;
	}
}
