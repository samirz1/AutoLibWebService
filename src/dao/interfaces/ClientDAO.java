package dao.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Client;
import dao.configuration.DaoFactory;
import dao.utilitaire.UtilitaireBaseDonnee;
import dao.utilitaire.UtilitaireMapping;

public class ClientDAO extends DAO<Client> {

	public ClientDAO(DaoFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public Boolean creation(Client objet) {
		System.out.println("CREATION");
		// Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int resultat = 0;
		String sql = new String(
				"INSERT INTO Client(nom, prenom, date_naissance) "
						+ " VALUES(?,?,?)");
		System.out.println(sql);
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,
							objet.getNom(), objet.getPrenom(),
							objet.getDateNaissance());
			resultat = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(resultat);
		return resultat == 1;
	}

	@Override
	public Boolean miseAjour(Client objet) {
		// Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int resultat = 0;
		String sql = new String(
				"UPDATE client SET nom = ? , prenom = ? , date_naissance = ?  WHERE idClient = ?");
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,
							objet.getNom(), objet.getPrenom(),
							objet.getDateNaissance(), objet.getIdClient());
			resultat = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat == 1;
	}

	@Override
	public Boolean supprimer(Client objet) {
		// Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int resultat = 0;
		String sql = new String("DELETE FROM client WHERE idClient = ?");
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,
							objet.getIdClient());
			resultat = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat == 1;
	}

	@Override
	public Client rechercher(Client objet) {
		// Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Client client = null;
		String sql = new String("SELECT * FROM client WHERE idClient = ?");
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql,
							objet.getIdClient());
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			client = UtilitaireMapping.mappingClient(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public List<Client> toutRechercher() {
		// Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Client client = null;
		List<Client> listeClients = new ArrayList<Client>();
		String sql = new String("SELECT *  FROM Client");
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql);
			resultSet = preparedStatement.executeQuery();
			// Etape 3 : Récuperation du resultat
			while (resultSet.next()) {
				client = UtilitaireMapping.mappingClient(resultSet);
				listeClients.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeClients;
	}

	/**
	 * Connexion Client
	 */
	public boolean connexionClient(String pseudo, String pwd) {
		// Etape 1 : Declaration
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Client client = null;
		String sql = new String("SELECT *  FROM Client WHERE login LIKE ? AND pwd LIKE ?");
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql, pseudo,pwd);
			resultSet = preparedStatement.executeQuery();
			// Etape 3 : Récuperation du resultat
			if (resultSet != null) {
				resultSet.next();
				client = UtilitaireMapping.mappingClient(resultSet);
				System.out.println(client.getPrenom());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client != null;
	}

}
