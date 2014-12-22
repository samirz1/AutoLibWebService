package dao.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.configuration.DaoFactory;
import dao.utilitaire.UtilitaireBaseDonnee;
import dao.utilitaire.UtilitaireMapping;
import beans.Borne;
import beans.Client;
import beans.Vehicule;

public class VehiculeDAO extends DAO<Vehicule> {

	public VehiculeDAO(DaoFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public Boolean creation(Vehicule objet) {
		// Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = new String("INSERT INTO Vehicule (idVehicule,RFID,etatBatterie,Disponibilite,latitude,longitude"
				+ "type_vehicule,idBorne,etatBorne,station,idVehicule VALUES (?,?,?,?,?,?,?,?,?,?,?)");
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,objet);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean miseAjour(Vehicule objet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean supprimer(Vehicule objet) {
		// TODO Auto-generated method stub
		return null;
	}

	public void rechercherVehiculeBorne(Borne borne) {
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
					.initialisationRequetePreparee(connection, sql,borne.getIdBorne());
			resultSet = preparedStatement.executeQuery();
			// Etape 3 : Récuperation du resultat
			while (resultSet.next()) {
				vehicule = UtilitaireMapping.mappingVehicule(resultSet);
				System.out.println(vehicule.getIdVehicule());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Vehicule> toutRechercher() {
		// Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Vehicule vehicule = null;
		List<Vehicule> vehicules = new ArrayList<Vehicule>();
		String sql = new String("SELECT *  FROM Vehicule");
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql);
			resultSet = preparedStatement.executeQuery();
			// Etape 3 : Récuperation du resultat
			while (resultSet.next()) {
				vehicule = UtilitaireMapping.mappingVehicule(resultSet);
				System.out.println(vehicule.getIdVehicule());
				vehicules.add(vehicule);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicules;
	}

	@Override
	public void rechercher(Vehicule objet) {
		// TODO Auto-generated method stub
		
	}

}
