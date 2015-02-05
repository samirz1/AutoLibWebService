package dao.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Station;
import beans.Vehicule;
import dao.configuration.DaoFactory;
import dao.utilitaire.UtilitaireBaseDonnee;
import dao.utilitaire.UtilitaireMapping;

public class VehiculeDAO extends DAO<Vehicule> {

	public VehiculeDAO(DaoFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public Boolean creation(Vehicule objet) {
		// Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Station station = null;
		// Preparation de l'objet
		objet.setRfid(Vehicule.generateRFID());
		objet.setEtatBatterie(100);
		objet.setDisponible("LIBRE");
		
		String sql = new String("SELECT *  FROM Station WHERE idStation = ? ");
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,objet.getStation());
			ResultSet resultSetStation = preparedStatement.executeQuery();
			while (resultSetStation.next()) {
				station = UtilitaireMapping.mappingStation(resultSetStation);
			}
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
			return false;
		}
		objet.setLatitude(station.getLatitude());
		objet.setLongitude(station.getLongitude());
		//Insertion dans la table vehicule
		sql = new String("INSERT INTO Vehicule (RFID,etatBatterie,Disponibilite,latitude,longitude,"
				+ "type_vehicule) VALUES (?,?,?,?,?,?)");
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,objet.getRfid(),objet.getEtatBatterie(),
							objet.getDisponible(),objet.getLatitude(),objet.getLongitude(),objet.getTypeVehicule());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
			return false;
		}
		//Affectation du v�hicule � la borne
		sql = new String("UPDATE Borne SET idVehicule = ? WHERE idBorne = ? ");
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,objet.getIdVehicule(),objet.getIdBorne());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean miseAjour(Vehicule objet) {
		// Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		connection = this.getDaoFactory().getConnection();
		
		Station station = null;
		String sql = new String("SELECT *  FROM Station WHERE idStation = ? ");
		
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,objet.getStation());
			ResultSet resultSetStation = preparedStatement.executeQuery();
			while (resultSetStation.next()) {
				station = UtilitaireMapping.mappingStation(resultSetStation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		objet.setLatitude(station.getLatitude());
		objet.setLongitude(station.getLongitude());
		
		//MAJ dans la table vehicule
		 sql = new String("UPDATE Vehicule SET RFID = ? , etatBatterie = ? , Disponibilite = ? "
				+ ", latitude = ? , longitude = ? , type_vehicule = ? "
				+ "WHERE idVehicule = ?");
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,objet.getRfid(),objet.getEtatBatterie(),
							objet.getDisponible(),objet.getLatitude(),objet.getLongitude(),objet.getTypeVehicule(),
							objet.getIdVehicule());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public Boolean supprimer(Vehicule objet) {
		// Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int resultat = 0;
		String sql = new String("UPDATE Borne SET idVehicule = null WHERE idVehicule = ?");
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,
							objet.getIdVehicule());
			resultat = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = new String("DELETE FROM vehicule WHERE idVehicule = ?");
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,
							objet.getIdVehicule());
			resultat = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat == 1;
	}

	public Vehicule rechercherVehiculeBorne(Vehicule objet) {
		// Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Vehicule vehicule = null;
		String sql = new String("SELECT *  FROM Vehicule, Borne WHERE Borne.idBorne= ? AND Vehicule.idVehicule=Borne.idVehicule");
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,objet.getIdBorne());
			resultSet = preparedStatement.executeQuery();
			// Etape 3 : Récuperation du resultat
			while (resultSet.next()) {
				vehicule = UtilitaireMapping.mappingVehicule(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicule;
	}

	@Override
	public List<Vehicule> toutRechercher() {
		// Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Vehicule vehicule = null;
		List<Vehicule> vehicules = new ArrayList<Vehicule>();
		String sql = new String("SELECT V.idVehicule,	RFID, etatBatterie, Disponibilite, latitude ,longitude,type_vehicule,idBorne,station  FROM Vehicule V LEFT JOIN Borne ON V.idVehicule = Borne.idVehicule");
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql);
			resultSet = preparedStatement.executeQuery();
			// Etape 3 : Récuperation du resultat
			while (resultSet.next()) {
				vehicule = UtilitaireMapping.mappingVehicule(resultSet);
				vehicule.setIdBorne(resultSet.getInt("idBorne"));
				vehicule.setStation(resultSet.getInt("station"));
				vehicules.add(vehicule);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicules;
	}

	@Override
	public Vehicule rechercher(Vehicule objet) {
		// Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Vehicule vehicule = null;
		String sql = new String("SELECT *  FROM Vehicule WHERE idVehicule = ?");
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,objet.getIdVehicule());
			resultSet = preparedStatement.executeQuery();
			// Etape 3 : Récuperation du resultat
			while (resultSet.next()) {
				vehicule = UtilitaireMapping.mappingVehicule(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		sql = "SELECT *  FROM Borne WHERE idVehicule = ?";
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,objet.getIdVehicule());
			resultSet = preparedStatement.executeQuery();
			// Etape 3 : Récuperation du resultat
			while (resultSet.next()) {
				vehicule.setIdBorne(resultSet.getInt("idBorne"));
				vehicule.setStation(resultSet.getInt("station"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vehicule;
	}
	
	public Boolean changeAffectation(Integer idVehicule, Integer ancienIdBorne,
			Integer nouveauIdBorne){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		//Suppression de l'anciene affectation 
		String sql = new String("UPDATE Borne SET idVehicule = NULL WHERE idBorne = ? ");
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,ancienIdBorne);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		//Ajout de la nouvelle affectation du v�hicule � la borne
		sql = new String("UPDATE Borne SET idVehicule = ? WHERE idBorne = ? ");
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,idVehicule,nouveauIdBorne);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
