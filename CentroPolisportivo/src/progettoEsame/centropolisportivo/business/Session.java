package progettoEsame.centropolisportivo.business;

import progettoEsame.centropolisportivo.exception.SessionException;

public class Session {

	private static Session instance; //istanza statica della classe
	private static boolean connected;//varibile per lo stato dell'utente
	private static String email;
	private static String typeUser;
	
	public static Session getInstance() {
		connected=false;
		if(instance == null)
			instance = new Session();
		return instance;
	}
	
	/*Funzione per la gestione della creazione della sessione con paraetro l'email dell'utente
	 * Effettuando un controllo sullo status della session si può verificare se l'utente è loggato
	 */
	public void createSession(String userId, String user) {
		email=userId;
		connected=true;
		typeUser=user;
	}
	
	/*
	 * Funzione per la distruzione della sessione con l'eliminazione di tutti i dati ad essa ennessi
	 */
	public void destroySession() {		
		email=null;
		connected=false;		
		typeUser=null;
	}
	
	/*
	 * Funzione per ottenere l'email dell'utente loggato dalla sessione
	 */
	public String getEmail() throws SessionException {
		if (email!=null){//effettua il controllo sull'email nel caso in cui sia già stata distrutta la sessione
			return email;
		}
		else 
			throw new SessionException(ConstantClass.NOT_LOGGED);
	}
	
	/*
	 * Funzione che restituisce lo stato della sessione, se l'utente è loggato o meno 
	 */
	public  boolean getStatusSession() {
		return connected;
	}
	
	/*
	 * Funzione che restituisce il tipo di utente logato
	 */
	public String getTypeUser(){
		return typeUser;
	}



}
