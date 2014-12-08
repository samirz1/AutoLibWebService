package dao.utilitaire;

import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Client;

public class UtilitaireMapping {

	public static Client mappingClient(ResultSet resultSet) throws SQLException{
		Client client = new Client();
		client.setIdClient(resultSet.getInt("idClient"));
		client.setNom(resultSet.getString("nom"));
		client.setPrenom(resultSet.getString("prenom"));
		client.setDateNaissance(resultSet.getDate("date_naissance"));
		return client;
	}
	
	//MAPPING DES AUTRES BEANS
	
}
