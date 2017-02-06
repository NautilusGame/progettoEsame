package progettoEsame.centropolisportivo.view.actionListener;

import progettoEsame.centropolisportivo.business.Session;

public class SessionCheck {

	private static Session instance;
	
	public static Session getInstance() {
		if(instance == null)
			instance = new Session();
		return instance;
	}
	
	/*
	 * Metodo per ottenere il tipo di utente dalla classe controller della view cosi da rispettare la struttura MVC
	 */
	public String getTypeUser(){
		return Session.getInstance().getTypeUser();
	}
}
