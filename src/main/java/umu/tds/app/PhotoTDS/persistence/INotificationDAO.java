package umu.tds.app.PhotoTDS.persistence;

import java.util.List;

import umu.tds.app.PhotoTDS.model.Notification;

public interface INotificationDAO {
	public void createNotification(Notification u);
	public Notification readNotification(int codigo);
	public void updateNotification(Notification u);
	public void deleteNotification(Notification u);
	public List<Notification> readAllNotifications();
}
