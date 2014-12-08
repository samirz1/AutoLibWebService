package dao.utilitaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilitaireBaseDonnee {

	// Initialise la requête préparée basée sur la connexion passée en argument,
	// avec la requête SQL et les objets donnés.
	public static PreparedStatement initialisationRequetePreparee(
			Connection connexion, String sql, Object... objets)
			throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(sql);
		for (int i = 0; i < objets.length; i++)
			preparedStatement.setObject(i + 1, objets[i]);
		return preparedStatement;
	}

	// Fermeture du resultset
	public static void fermetureRessource(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				System.out.println("PROBLEME FERMETURE DE LA RESSOURCE RESULTSAT");
			}
		}
	}

	// Fermeture du statement
	public static void fermetureRessource(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				System.out.println("PROBLEME FERMETURE DE LA RESSOURCE STATEMENT");
			}
		}
	}

	// Fermeture de la connexion
	public static void fermetureRessource(Connection connexion) {
		if (connexion != null) {
			try {
				connexion.close();
			} catch (SQLException e) {
				System.out.println("PROBLEME FERMETURE DE LA RESSOURCE CONNEXION");
			}
		}
	}

	// Fermetures du statement et de la connexion
	public static void fermeturesRessources(Statement statement,
			Connection connexion) {
		fermetureRessource(statement);
		fermetureRessource(connexion);
	}

	// Fermetures du resultset, du statement et de la connexion
	public static void fermeturesRessources(ResultSet resultSet,
			Statement statement, Connection connexion) {
		fermetureRessource(resultSet);
		fermetureRessource(statement);
		fermetureRessource(connexion);
	}

}
