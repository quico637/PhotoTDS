package umu.tds.app.PhotoTDS.persistence;

import java.util.List;

import umu.tds.app.PhotoTDS.model.Comentario;

public interface IComentarioDAO {
	public void createComentario(Comentario u);
	public Comentario readComentario(int codigo);
	public void updateComentario(Comentario u);
	public void deleteComentario(Comentario u);
	public List<Comentario> readAllComentarios();
}
