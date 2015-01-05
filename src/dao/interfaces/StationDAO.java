package dao.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Borne;
import beans.Station;
import dao.configuration.DaoFactory;
import dao.utilitaire.UtilitaireBaseDonnee;
import dao.utilitaire.UtilitaireMapping;

public class StationDAO extends DAO<Station> {

	protected static String TABLE = "station";
	protected static String ID = "idStation";
	
	public StationDAO(DaoFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public Boolean creation(Station objet) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		// requête d'insertion de l'objet
		String sql = "INSERT INTO " + TABLE
				+ " (id, latitude, longitude, adresse, numero, ville)"
				+ " VALUES ("
				+ (objet.getIdStation() == null ? "NULL" : "'" + objet.getIdStation() + "'") + ","
				+ " '" + objet.getLatitude() + "',"
				+ " '" + objet.getLongitude() + "',"
				+ " '" + objet.getAdresse() + "',"
				+ " '" + objet.getNumero() + "',"
				+ " '" + objet.getVille() + "'"
				+ ")";

		try {
			connexion = this.getDaoFactory().getConnection();
			preparedStatement = UtilitaireBaseDonnee.initialisationRequetePreparee(connexion, sql);
			preparedStatement.executeQuery();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean miseAjour(Station objet) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		// requête de mise à jour de l'objet
		String sql = "UPDATE " + TABLE
				+ " SET"
				+ " latitude = '" + objet.getLatitude() + "',"
				+ " longitude = '" + objet.getLongitude() + "',"
				+ " adresse = '" + objet.getAdresse() + "',"
				+ " numero = '" + objet.getNumero() + "',"
				+ " ville = '" + objet.getVille() + "'"
				+ " WHERE " + ID + " = '" + objet.getIdStation() + "'"
				+ " LIMIT 1";

		try {
			connexion = this.getDaoFactory().getConnection();
			preparedStatement = UtilitaireBaseDonnee.initialisationRequetePreparee(connexion, sql);
			preparedStatement.executeQuery();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean supprimer(Station objet) {
		
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		// requête de suppression de l'objet
		String sql = "DELETE FROM " + TABLE
				+ " WHERE " + ID + " = '" + objet.getIdStation() + "'"
				+ " LIMIT 1";

		try {
			connexion = this.getDaoFactory().getConnection();
			preparedStatement = UtilitaireBaseDonnee.initialisationRequetePreparee(connexion, sql);
			preparedStatement.executeQuery();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void rechercher(Station objet) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Station> toutRechercher() {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Station station =  null;
		List<Station> stations =new ArrayList<Station>();
		
		String sql = "SELECT * FROM " + TABLE;

		try {
			connexion = this.getDaoFactory().getConnection();
			preparedStatement = UtilitaireBaseDonnee.initialisationRequetePreparee(connexion, sql);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				station = UtilitaireMapping.mappingStation(resultSet);
				stations.add(station);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return stations;
	}

}
