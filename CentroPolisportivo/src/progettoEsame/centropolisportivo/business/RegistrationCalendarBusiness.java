package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;

import progettoEsame.centropolisportivo.model.RegistrationCalendar;
import progettoEsame.centropolisportivo.view.actionListener.ModifyRegistrationController;

public class RegistrationCalendarBusiness {

	private static RegistrationCalendarBusiness instance;


	public static synchronized RegistrationCalendarBusiness getInstance()
	{
		if(instance == null)
		{
			return new RegistrationCalendarBusiness();
		}
		return instance;
	}
	
	public void insert(RegistrationCalendar newRegistrationCalendar)throws SQLException
	{
		RegistrationCalendar.insert(newRegistrationCalendar);
	}
}
