package exception;

//RESPONBLE DES EXCEPTION LIEE A LA CONFIGURATION (JDBC, DAO, ...)
public class DaoConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DaoConfigurationException(String message) {
		super(message);
	}

}
