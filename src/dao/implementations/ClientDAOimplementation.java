package dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Borne;
import beans.Client;
import dao.configuration.DaoFactory;
import dao.interfaces.BorneDAO;
import dao.interfaces.ClientDAO;
import dao.utilitaire.UtilitaireBaseDonnee;
import dao.utilitaire.UtilitaireMapping;

public class ClientDAOimplementation implements BorneDAO, ClientDAO{

	private DaoFactory daoFactory;
	
	public ClientDAOimplementation(DaoFactory daoFactory) {
		super();
		this.setDaoFactory(daoFactory);
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
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Borne> listeBornes = new ArrayList<Borne>();
		
		connection = this.getDaoFactory().getConnection();
		try {
			preparedStatement = UtilitaireBaseDonnee.initialisationRequetePreparee(connection, "SELECT * FROM Client");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Client client = UtilitaireMapping.mappingClient(resultSet);
				System.out.println(client.getNom());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return listeBornes;
	}

	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

}
