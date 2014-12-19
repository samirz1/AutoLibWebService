package dao.interfaces;

import java.util.List;

import dao.configuration.DaoFactory;
import beans.Borne;

public class TypeVehiculeDAO extends DAO<Borne> {

	public TypeVehiculeDAO(DaoFactory daoFactory) {
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
