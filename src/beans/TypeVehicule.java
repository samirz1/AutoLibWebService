package beans;

import java.io.Serializable;

public class TypeVehicule implements Serializable {

	// *******************************************
	// MEMBRES
	// *******************************************
	private static final long serialVersionUID = 1L;
	private Integer idTypeVehicule;
	private String categorie;
	private String typeVehicule;

	// *******************************************
	// CONSTRUCTEUR
	// *******************************************
	public TypeVehicule() {
		super();
	}

	// *******************************************
	// GETTERS & SETTERS
	// *******************************************
	public Integer getIdTypeVehicule() {
		return idTypeVehicule;
	}

	public void setIdTypeVehicule(Integer idTypeVehicule) {
		this.idTypeVehicule = idTypeVehicule;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getTypeVehicule() {
		return typeVehicule;
	}

	public void setTypeVehicule(String typeVehicule) {
		this.typeVehicule = typeVehicule;
	}

}
