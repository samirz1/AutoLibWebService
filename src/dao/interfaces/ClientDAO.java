package dao.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.Borne;
import beans.Client;
import dao.configuration.DaoFactory;
import dao.utilitaire.UtilitaireBaseDonnee;
import dao.utilitaire.UtilitaireMapping;

public class ClientDAO extends DAO<Borne> {

	public ClientDAO(DaoFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public Boolean creation(Borne objet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean miseAjour(Borne objet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean supprimer(Borne objet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rechercher(Borne objet) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Borne> toutRechercher() {
		// Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Client client = null;
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
				System.out.println(client.getPrenom());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Connexion Client
	 */
	public boolean connexionClient(String pseudo) {
		// Etape 1 : Declaration
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Client client = null;
		String sql = new String("SELECT *  FROM Client WHERE NOM LIKE ?");
		// Etape 2 : Preparation et execution
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee
					.initialisationRequetePreparee(connection, sql, pseudo);
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
