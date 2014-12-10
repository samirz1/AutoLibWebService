package dao.interfaces;

import java.util.List;


public interface DAO<T> {

	public Boolean creation(T objet);

	public Boolean miseAjour(T objet);

	public Boolean supprimer(T objet);

	public void rechercher(T objet);
	
	public List<T> toutRechercher();

}
