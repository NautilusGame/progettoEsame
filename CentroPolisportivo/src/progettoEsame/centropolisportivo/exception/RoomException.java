package progettoEsame.centropolisportivo.exception;

public class RoomException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public RoomException (String message) {
		super (message);
	}

	public RoomException (Throwable cause) {
		super (cause);
	}

	public RoomException (String message, Throwable cause) {
		super (message, cause);
	}

}
