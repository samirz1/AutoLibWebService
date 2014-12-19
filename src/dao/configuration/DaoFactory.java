package dao.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.interfaces.BorneDAO;
import dao.interfaces.ClientDAO;
import dao.interfaces.ReservationDAO;
import dao.interfaces.StationDAO;
import dao.interfaces.TypeVehiculeDAO;
import dao.interfaces.UtiliseDAO;
import dao.interfaces.VehiculeDAO;
import exception.DaoConfigurationException;

public class DaoFactory {

	// *******************************************
	// MEMBRES
	// *******************************************
	private final String url = "jdbc:mysql://localhost:3306/autolib";
	private final String utilisateur = "userjavaee";
	private final String motDePasse = "javaee";

	// *******************************************
	// CONSTRUCTEUR
	// *******************************************
	private DaoFactory() {
		super();
	}

	// *******************************************
	// METHODES
	// *******************************************
	public static DaoFactory getInstance() throws DaoConfigurationException {
		// Chargement du driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new DaoConfigurationException(
					"PROBLEME DU CHARGEMENT DU DRIVER");
		}
		return new DaoFactory();
	}

	// méthode qui retourn une connection ouverte avec la base de donnée
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(this.url,
					this.utilisateur, this.motDePasse);
		} catch (SQLException e) {
			throw new DaoConfigurationException(
					"PROBLEME LORS DE LA CONNECXION A LA BASE DE DONNEE");
		}
		return connection;
	}

	// *******************************************
	// GETTERS DES DAO
	// *******************************************
	public BorneDAO getBorneDAO() {
		return new BorneDAO(this);
	}

	public ClientDAO getClientDAO() {
		return new ClientDAO(this);
	}

	public ReservationDAO getReservationDAO() {
		return new ReservationDAO(this);
	}

	public StationDAO getStationDAO() {
		return new StationDAO(this);
	}

	public TypeVehiculeDAO getTypeVehiculeDAO() {
		return new TypeVehiculeDAO(this);
	}

	public UtiliseDAO getUtiliseDAO() {
		return new UtiliseDAO(this);
	}

	public VehiculeDAO getVehiculeDAO() {
		return new VehiculeDAO(this);
	}
}
