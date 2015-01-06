package dao.interfaces;

import java.util.List;

import beans.Reservation;
import dao.configuration.DaoFactory;

public class ReservationDAO extends DAO<Reservation> {

	public ReservationDAO(DaoFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public Boolean creation(Reservation objet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean miseAjour(Reservation objet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean supprimer(Reservation objet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation rechercher(Reservation objet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> toutRechercher() {
		// TODO Auto-generated method stub
		return null;
	}

}
