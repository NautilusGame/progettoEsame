package progettoEsame.centropolisportivo.exception;

public class LevelException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public LevelException (String message) {
		super (message);
	}

	public LevelException (Throwable cause) {
		super (cause);
	}

	public LevelException (String message, Throwable cause) {
		super (message, cause);
	}


}
