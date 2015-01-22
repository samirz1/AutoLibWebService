package dao.utilitaire;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import beans.Borne;
import beans.Client;
import beans.Reservation;
import beans.Station;
import beans.TypeVehicule;
import beans.Utilise;
import beans.Vehicule;

public class UtilitaireMapping {

	public static Client mappingClient(ResultSet resultSet) throws SQLException{
		Client client = new Client();
		client.setIdClient(resultSet.getInt("idClient"));
		client.setNom(resultSet.getString("nom"));
		client.setPrenom(resultSet.getString("prenom"));
		client.setDateNaissance(resultSet.getDate("date_naissance"));
		client.setLogin(resultSet.getString("login"));
		client.setPwd(resultSet.getString("pwd"));
		return client;
	}
	
	public static Vehicule mappingVehicule(ResultSet resultSet) throws SQLException{
		Vehicule vehicule = new Vehicule();
		vehicule.setIdVehicule(resultSet.getInt("idVehicule"));
		vehicule.setRfid(resultSet.getInt("RFID"));
		vehicule.setEtatBatterie(resultSet.getInt("etatBatterie"));
		vehicule.setDisponible(resultSet.getString("Disponibilite"));
		vehicule.setLatitude(resultSet.getDouble("latitude"));
		vehicule.setLongitude(resultSet.getDouble("longitude"));
		vehicule.setTypeVehicule(resultSet.getInt("type_vehicule"));
		return vehicule;
	}
	
	public static Station mappingStation(ResultSet resultSet) throws SQLException{
		Station station = new Station();
		station.setIdStation(resultSet.getInt("idStation"));
		station.setLatitude(resultSet.getDouble("latitude"));
		station.setLongitude(resultSet.getDouble("longitude"));
		station.setAdresse(resultSet.getString("adresse"));
		station.setNumero(resultSet.getInt("numero"));
		station.setVille(resultSet.getString("ville"));
		station.setCodePostal(resultSet.getInt("code_postal"));
		return station;
	}
	
	public static TypeVehicule mappingTypeVehicule(ResultSet resultSet) throws SQLException{
		TypeVehicule typeVehicule = new TypeVehicule();
		typeVehicule.setIdTypeVehicule(resultSet.getInt("idType_vehicule"));
		typeVehicule.setCategorie(resultSet.getString("categorie"));
		typeVehicule.setTypeVehicule(resultSet.getString("type_vehicule"));
		return typeVehicule;
	}
	
	public static Borne mappingBorne(ResultSet resultSet) throws SQLException{
		Borne borne = new Borne();
		borne.setIdBorne(resultSet.getInt("idBorne"));
		borne.setEtatBorne(resultSet.getInt("etatBorne"));
		borne.getStation().setIdStation(resultSet.getInt("station"));
		borne.getVehicule().setIdVehicule(resultSet.getInt("idVehicule"));
		return borne;
	}
	
	public static Reservation mappingReservation(ResultSet resultSet) throws SQLException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Reservation reservation = new Reservation();
		reservation.getVehicule().setIdVehicule(resultSet.getInt("vehicule"));
		reservation.getClient().setIdClient(resultSet.getInt("client"));
		try {
			reservation.setDateReservation(format.parse(resultSet.getString("date_reservation")));
			reservation.setDateEcheance(format.parse(resultSet.getString("date_echeance")));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return reservation;
	}
	
	public static Utilise mappingUtilise(ResultSet resultSet) throws SQLException{
		Utilise utilise = new Utilise();
		utilise.getVehicule().setIdVehicule(resultSet.getInt("Vehicule"));
		utilise.getClient().setIdClient(resultSet.getInt("Client"));
		utilise.setDate(new DateTime(resultSet.getTimestamp("date")));
		utilise.getBorneDepart().setIdBorne(resultSet.getInt("borne_depart"));
		utilise.getBorneArrivee().setIdBorne(resultSet.getInt("borne_arrivee"));
		return utilise;
	}
	
}
