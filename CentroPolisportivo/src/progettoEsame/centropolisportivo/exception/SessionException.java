package progettoEsame.centropolisportivo.exception;

public class SessionException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SessionException (String message) {
		super (message);
	}

	public SessionException (Throwable cause) {
		super (cause);
	}

	public SessionException (String message, Throwable cause) {
		super (message, cause);
	}
}
