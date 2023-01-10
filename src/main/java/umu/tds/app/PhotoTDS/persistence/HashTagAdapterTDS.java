package umu.tds.app.PhotoTDS.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.app.PhotoTDS.model.HashTag;

public class HashTagAdapterTDS implements IHashTagDAO {
	private static ServicioPersistencia servPersistencia;
	private static HashTagAdapterTDS unicaInstancia = null;

	public static IHashTagDAO getInstance() { // patron singleton
		if (unicaInstancia == null)
			return new HashTagAdapterTDS();
		else
			return unicaInstancia;
	}

	private HashTagAdapterTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public void createHashTag(HashTag h) {
		Entidad eHashTag = null;

		// Si la entidad esta registrada no la registra de nuevo
		try {
			eHashTag = servPersistencia.recuperarEntidad(h.getCodigo());
		} catch (NullPointerException e) {
			
		}
		if (eHashTag != null)
			return;

		// crear entidad Cliente
		eHashTag = new Entidad();
		eHashTag.setNombre("hashtag");
		eHashTag.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad("name", h.getName())
				)));

		// registrar entidad cliente
		eHashTag = servPersistencia.registrarEntidad(eHashTag);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		h.setCodigo(eHashTag.getId());
		
	}
	public HashTag readHashTag(int codigo) {
		
		// Si la entidad esta en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (HashTag) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		// si no, la recupera de la base de datos
		Entidad eHashTag;

		String name;
		
		// recuperar entidad
		eHashTag = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		name = servPersistencia.recuperarPropiedadEntidad(eHashTag, "name");
		HashTag c;
		c = HashTag.createHashtag(name);
		if(c == null) {
			return null;
		}
		c.setCodigo(codigo);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, c);
		
		return c;
	}
	public void updateHashTag(HashTag h) {
		Entidad eHashTag = servPersistencia.recuperarEntidad(h.getCodigo());

		for (Propiedad prop : eHashTag.getPropiedades()) {
			if (prop.getNombre().equals("name")) {
				prop.setValor(h.getName());
			}

			servPersistencia.modificarPropiedad(prop);
		}
	}
	
	public void deleteHashTag(HashTag h) {
		Entidad eHashTag = servPersistencia.recuperarEntidad(h.getCodigo());

		servPersistencia.borrarEntidad(eHashTag);
	}
	
	public List<HashTag> readAllHashTags() {
		List<Entidad> eHashTag = servPersistencia.recuperarEntidades("hashtag");
		List<HashTag> l = new LinkedList<>();

		for (Entidad eComment : eHashTag) {
			l.add(readHashTag(eComment.getId()));
		}
		return l;
	}
}
