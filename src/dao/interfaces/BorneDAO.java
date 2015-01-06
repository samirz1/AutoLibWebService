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

	public BorneDAO(DaoFactory daoFactory) {
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
	public Borne rechercher(Borne objet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Borne> toutRechercher() {
		//Etape 1 : Declarations
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Borne borne =  null;
		List<Borne> bornes=new ArrayList<Borne>();
		String sql = new String("SELECT *  FROM Borne");
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
