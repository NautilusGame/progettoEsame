package progettoEsame.centropolisportivo.exception;

public class ActivityTypeException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ActivityTypeException (String message) {
		super (message);
	}

	public ActivityTypeException (Throwable cause) {
		super (cause);
	}

	public ActivityTypeException (String message, Throwable cause) {
		super (message, cause);
	}

}
