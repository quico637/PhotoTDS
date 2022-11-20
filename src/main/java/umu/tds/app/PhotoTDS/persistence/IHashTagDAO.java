package umu.tds.app.PhotoTDS.persistence;

import java.util.List;

import umu.tds.app.PhotoTDS.model.HashTag;

public interface IHashTagDAO {
	public void createHashTag(HashTag u);
	public HashTag readHashTag(int codigo);
	public void updateHashTag(HashTag u);
	public void deleteHashTag(HashTag u);
	public List<HashTag> readAllHashTags();
}
