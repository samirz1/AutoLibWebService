package dao.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
				+ " (" + ID + ", latitude, longitude, adresse, numero, ville, code_postal)"
				+ " VALUES ("
				+ (objet.getIdStation() == null ? "NULL" : "'" + objet.getIdStation() + "'") + ","
				+ " '" + objet.getLatitude() + "',"
				+ " '" + objet.getLongitude() + "',"
				+ " '" + objet.getAdresse() + "',"
				+ " '" + objet.getNumero() + "',"
				+ " '" + objet.getVille() + "',"
				+ " '" + objet.getCodePostal() + "'"
				+ ")";

		try {
			connexion = this.getDaoFactory().getConnection();
			preparedStatement = UtilitaireBaseDonnee.initialisationRequetePreparee(connexion, sql);
			preparedStatement.executeUpdate();
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
				+ " ville = '" + objet.getVille() + "',"
				+ " code_postal = '" + objet.getCodePostal() + "'"
				+ " WHERE " + ID + " = '" + objet.getIdStation() + "'"
				+ " LIMIT 1";

		try {
			connexion = this.getDaoFactory().getConnection();
			preparedStatement = UtilitaireBaseDonnee.initialisationRequetePreparee(connexion, sql);
			preparedStatement.executeUpdate();
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
			preparedStatement.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Station rechercher(Station objet) {
		List<Station> ls = rechercheAvancee(objet);
		if(ls.size() > 0) {
			return ls.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Recherche avancée de stations
	 * @param objet
	 * @return
	 */
	public List<Station> rechercheAvancee(Station objet) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Station station =  null;
		List<Station> stations = new ArrayList<Station>();
		
		String sql = "SELECT * FROM " + TABLE + " WHERE 1";
		
		if(objet != null) {
			if(objet.getIdStation() != 0)
				sql += " AND " + ID + " = '" + objet.getIdStation() + "'";
			if(objet.getAdresse() != null && objet.getAdresse().length() > 0)
				sql += " AND adresse = '" + objet.getAdresse() + "'";
			if(objet.getCodePostal() != null && objet.getCodePostal() > 0)
				sql += " AND code_postal = '" + objet.getCodePostal() + "'";
			if(objet.getLatitude() != null && objet.getLatitude() > 0)
				sql += " AND latitude = '" + objet.getLatitude() + "'";
			if(objet.getLongitude() != null && objet.getLongitude() > 0)
				sql += " AND longitude = '" + objet.getLongitude() + "'";
			if(objet.getNumero() != null && objet.getNumero() > 0)
				sql += " AND numero = '" + objet.getNumero() + "'";
			if(objet.getVille() != null && objet.getVille().length() > 0)
				sql += " AND ville = '" + objet.getVille() + "'";
		}

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
	
	/**
	 * Rechercher une liste de stations à partir
	 * d'un mot clé (soit l'id, soit la ville, soit l'adresse)
	 * @param keyword
	 * @return liste des stations
	 */
	public List<Station> rechercherMotCle(String keyword) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Station station =  null;
		List<Station> stations = new ArrayList<Station>();
		
		String sql = "SELECT * FROM " + TABLE;
		
		if(keyword != null && keyword.length() > 0) {
			sql += " WHERE " + ID + " = '" + keyword + "'";
			sql += " OR adresse LIKE '%" + keyword + "%'";
			sql += " OR ville LIKE '%" + keyword + "%'";
			sql += " OR code_postal = '" + keyword + "'";
		}

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

	/**
	 * Rechercher toutes les stations de la base de données
	 * @return liste des stations
	 */
	@Override
	public List<Station> toutRechercher() {
		return rechercherMotCle(null);
	}

}
