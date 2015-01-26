package dao.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Borne;
import dao.configuration.DaoFactory;
import dao.utilitaire.UtilitaireBaseDonnee;
import dao.utilitaire.UtilitaireMapping;

public class BorneDAO extends DAO<Borne> {
	
	protected static String TABLE = "borne";
	protected static String ID = "idBorne";

	public BorneDAO(DaoFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public Boolean creation(Borne objet) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		// requête d'insertion de l'objet
		String sql = "INSERT INTO " + TABLE + " ("
				+ ID + ", etatBorne, station, idVehicule)"
				+ " VALUES (?, ?, ?, ?)";

		try {
			connexion = this.getDaoFactory().getConnection();
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connexion, sql, 
							objet.getIdBorne(),
							objet.getEtatBorne() == -1 ? 0 : objet.getEtatBorne(),
							objet.getStation().getIdStation(),
							objet.getVehicule().getIdVehicule() == 0 ? null : objet.getVehicule().getIdVehicule());
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean miseAjour(Borne objet) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		// requête de mise à jour de l'objet
		String sql = new String(
				"UPDATE " + TABLE + " SET etatBorne = ? , station = ? , idVehicule = ?  WHERE " + ID + " = ?");
		try {
			connexion = this.getDaoFactory().getConnection();
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connexion, sql,
							objet.getEtatBorne() == -1 ? 0 : objet.getEtatBorne(),
							objet.getStation().getIdStation(),
							(objet.getVehicule() == null || objet.getVehicule().getIdVehicule() == 0) ? null : objet.getVehicule().getIdVehicule(),
							objet.getIdBorne());
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean supprimer(Borne objet) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		
		// requête de suppression de l'objet
				String sql = "DELETE FROM " + TABLE + " WHERE " + ID  + " = ?";
		try {
			connexion = this.getDaoFactory().getConnection();
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connexion, sql, objet.getIdBorne());
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Borne rechercher(Borne objet) {
		List<Borne> lb = rechercheAvancee(objet);
		if(lb.size() > 0) {
			return lb.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * Recherche avancée de stations
	 * @param objet
	 * @return
	 */
	public List<Borne> rechercheAvancee(Borne objet) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Borne borne =  null;
		List<Borne> bornes = new ArrayList<Borne>();
		
		String sql = "SELECT * FROM " + TABLE + " WHERE 1";
		
		if(objet != null) {
			if(objet.getIdBorne() != null && objet.getIdBorne() > 0)
				sql += " AND " + ID + " = '" + objet.getIdBorne() + "'";
			if(objet.getEtatBorne() != null)
				sql += " AND etatBorne = '" + objet.getEtatBorne() + "'";
			if(objet.getStation().getIdStation() != null && objet.getStation().getIdStation() > 0)
				sql += " AND station = '" + objet.getStation().getIdStation() + "'";
			if(objet.getVehicule().getIdVehicule() != null && objet.getVehicule().getIdVehicule() > 0)
				sql += " AND idVehicule = '" + objet.getVehicule().getIdVehicule() + "'";
		}

		try {
			connexion = this.getDaoFactory().getConnection();
			preparedStatement = UtilitaireBaseDonnee.initialisationRequetePreparee(connexion, sql);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				borne = UtilitaireMapping.mappingBorne(resultSet);
				bornes.add(borne);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return bornes;
	}

	@Override
	public List<Borne> toutRechercher() {
		//Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Borne borne =  null;
		List<Borne> bornes=new ArrayList<Borne>();
		String sql = new String("SELECT * FROM " + TABLE);
		//Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee.initialisationRequetePreparee(connection, sql);
			resultSet = preparedStatement.executeQuery();
			//Etape 3 : Récuperation du resultat
			while(resultSet.next()){
				borne = UtilitaireMapping.mappingBorne(resultSet);
				bornes.add(borne);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return bornes;
	}

}
