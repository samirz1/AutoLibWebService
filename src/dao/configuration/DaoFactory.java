package dao.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.implementations.ClientDAOimplementation;
import dao.interfaces.ClientDAO;
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
	public static DaoFactory getInstance() throws DaoConfigurationException{
		//Chargement du driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			throw new DaoConfigurationException("PROBLEME DU CHARGEMENT DU DRIVER");
		}
		return new DaoFactory();
	}
	
	//méthode qui retourn une connection ouverte avec la base de donnée
	public Connection getConnection(){
		Connection connection =  null;
		try {
			connection = DriverManager.getConnection(this.url, this.utilisateur, this.motDePasse);
		} catch (SQLException e) {
			throw new DaoConfigurationException("PROBLEME LORS DE LA CONNECXION A LA BASE DE DONNEE");
		}
		return connection;
	}
	
	// *******************************************
	// GETTERS DES DAO
	// *******************************************
	public ClientDAO getClientDAO(){
		return new ClientDAOimplementation(this);
	}
	
}
