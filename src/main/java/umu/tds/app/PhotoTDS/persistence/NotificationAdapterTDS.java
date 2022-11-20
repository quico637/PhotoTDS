package umu.tds.app.PhotoTDS.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.app.PhotoTDS.model.Notification;
import umu.tds.app.PhotoTDS.model.Utils;

public class NotificationAdapterTDS implements INotificationDAO {
	private static ServicioPersistencia servPersistencia;
	private static NotificationAdapterTDS unicaInstancia = null;

	public static INotificationDAO getInstance() { // patron singleton
		if (unicaInstancia == null)
			return new NotificationAdapterTDS();
		else
			return unicaInstancia;
	}

	private NotificationAdapterTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	public void createNotification(Notification n) {
		Entidad eNotification = null;

		// Si la entidad esta registrada no la registra de nuevo
		try {
			eNotification = servPersistencia.recuperarEntidad(n.getCodigo());
		} catch (NullPointerException e) {
			
		}
		if (eNotification != null)
			return;

		// crear entidad Cliente
		eNotification = new Entidad();
		eNotification.setNombre("notification");
		eNotification.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad("date", Utils.DateToString(n.getDate())),
				new Propiedad("text", n.getText())
				)));

		// registrar entidad cliente
		eNotification = servPersistencia.registrarEntidad(eNotification);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		n.setCodigo(eNotification.getId());
		
	}
	public Notification readNotification(int codigo) {
		// si no, la recupera de la base de datos
		Entidad eNotification;

		String date;
		String text;
		
		// recuperar entidad
		eNotification = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		date = servPersistencia.recuperarPropiedadEntidad(eNotification, "date");
		text = servPersistencia.recuperarPropiedadEntidad(eNotification, "fechaPublicacion");
		Notification c;
		c = new Notification(Utils.StringToDate(date), text);
		c.setCodigo(codigo);
		return c;
	}
	public void updateNotification(Notification n) {
		Entidad eNotification = servPersistencia.recuperarEntidad(n.getCodigo());

		for (Propiedad prop : eNotification.getPropiedades()) {
			if (prop.getNombre().equals("date")) {
				prop.setValor(Utils.DateToString(n.getDate()));
			} else if (prop.getNombre().equals("text")) {
				prop.setValor(n.getText());
			}

			servPersistencia.modificarPropiedad(prop);
		}
	}
	public void deleteNotification(Notification n) {
		Entidad eNotification = servPersistencia.recuperarEntidad(n.getCodigo());

		servPersistencia.borrarEntidad(eNotification);
	}
	public List<Notification> readAllNotifications() {
		List<Entidad> eNotification = servPersistencia.recuperarEntidades("notification");
		List<Notification> Notifications = new LinkedList<>();

		for (Entidad eComment : eNotification) {
			Notifications.add(readNotification(eComment.getId()));
		}
		return Notifications;
	}
}
