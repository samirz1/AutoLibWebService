package dao.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.*;
import dao.configuration.DaoFactory;
import dao.utilitaire.UtilitaireBaseDonnee;
import dao.utilitaire.UtilitaireMapping;

public class ReservationDAO extends DAO<Reservation> {
	protected static String TABLE = "reservation";
	protected static String ID = "idReservation";

	public ReservationDAO(DaoFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public Boolean creation(Reservation objet) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		// requête d'insertion de l'objet
		String sql = "INSERT INTO " + TABLE + " ("
				+ "vehicule, client, date_reservation, date_echeance)"
				+ " VALUES (?, ?, ?, ?)";

		try {
			connexion = this.getDaoFactory().getConnection();
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connexion, sql, objet
							.getVehicule().getIdVehicule(), objet.getClient()
							.getIdClient(), objet.getDateReservation(), objet
							.getDateEcheance());
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean miseAjour(Reservation objet) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		// requête de mise à jour de l'objet
		String sql = new String(
				"UPDATE reservation SET date_reservation = ? , date_echeance = ?  WHERE vehicule = ? AND client = ?");
		try {
			connexion = this.getDaoFactory().getConnection();
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connexion, sql, objet.getDateReservation(), objet.getDateEcheance(),
							objet.getVehicule().getIdVehicule(),objet.getClient().getIdClient() );
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean supprimer(Reservation objet) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		
		// requête de suppression de l'objet
				String sql = "DELETE FROM " + TABLE
						+ " WHERE vehicule "  + " = '" + objet.getVehicule().getIdVehicule() + "'"
						+ " AND client "  + " = '" + objet.getClient().getIdClient() + "'";
		try {
			connexion = this.getDaoFactory().getConnection();
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connexion, sql);
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Reservation rechercher(Reservation objet) {
		// Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Reservation resa = null;
		String sql = new String("SELECT * FROM reservation WHERE vehicule = ? AND client = ? ");
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,objet.getVehicule().getIdVehicule(),
							objet.getClient().getIdClient());
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			resa = UtilitaireMapping.mappingReservation(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resa;
	}
	
	public Reservation rechercherParClient(Reservation objet) {
		// Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Reservation resa = null;
		String sql = new String("SELECT * FROM reservation WHERE client = ? ");
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql, objet.getClient().getIdClient());
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			resa = UtilitaireMapping.mappingReservation(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resa;
	}

	@Override
	public List<Reservation> toutRechercher() {
		// Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Reservation resa = null;
		List<Reservation> listeReservations = new ArrayList<Reservation>();
		String sql = new String("SELECT *  FROM reservation");
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql);
			resultSet = preparedStatement.executeQuery();
			// Etape 3 : Récuperation du resultat
			while (resultSet.next()) {
				resa = UtilitaireMapping.mappingReservation(resultSet);
				listeReservations.add(resa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeReservations;
	}


}
