package dao.interfaces;

import java.util.List;

import beans.Borne;
import dao.configuration.DaoFactory;

public class StationDAO extends DAO<Borne> {

	public StationDAO(DaoFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public Boolean creation(Borne objet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean miseAjour(Borne objet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean supprimer(Borne objet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rechercher(Borne objet) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Borne> toutRechercher() {
		// TODO Auto-generated method stub
		return null;
	}

}
