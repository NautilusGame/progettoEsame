package progettoEsame.centropolisportivo.view.actionListener;

import java.util.Date;

public class LoginRegisterController {
	
	private static LoginRegisterController instance;
	public static synchronized LoginRegisterController getInstance ()
	{
		if(instance==null)
			instance = new LoginRegisterController();
		return instance;
	}
	
	public boolean checkDate(Date d)
	{
		if(d.after(new Date()))
			return true;
		return false;
	}

}
