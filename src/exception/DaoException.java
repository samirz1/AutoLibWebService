package exception;

//RESPONSABLE DES EXCEPTIONS LIEE A LA COMMUNICATION AVEC LA BASE DE DONNEE
public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DaoException(String message) {
		super(message);
	}

}
