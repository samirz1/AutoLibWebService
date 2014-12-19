package dao.interfaces;

import java.util.List;

import dao.configuration.DaoFactory;

public abstract class DAO<T> {

	private DaoFactory daoFactory;
	
	protected DAO(DaoFactory daoFactory){
		this.setDaoFactory(daoFactory);
	}
	
	public abstract Boolean creation(T objet);

	public abstract Boolean miseAjour(T objet);

	public abstract Boolean supprimer(T objet);

	public abstract void rechercher(T objet);

	public abstract List<T> toutRechercher();

	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

}
