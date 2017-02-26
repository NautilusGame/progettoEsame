package progettoEsame.centropolisportivo.view.actionListener;

import java.sql.SQLException;
import java.util.ArrayList;
import progettoEsame.centropolisportivo.business.LevelBusiness;
import progettoEsame.centropolisportivo.business.RegistrationBusiness;
import progettoEsame.centropolisportivo.business.ScheduleBusiness;
import progettoEsame.centropolisportivo.model.Level;
import progettoEsame.centropolisportivo.model.Registration;
import progettoEsame.centropolisportivo.model.Schedule;

public class ModifyRegistrationController {
	
	private static ModifyRegistrationController instance;


	public static synchronized ModifyRegistrationController getInstance()
	{
		if(instance == null)
		{
			return new ModifyRegistrationController();
		}
		return instance;
	}
	
	public Registration getRegistration(int id)
	{
		try {
			return RegistrationBusiness.getInstance().findById(id);
		} catch (SQLException e) {
		}
		return null;
	}
	
	public ArrayList<Level> getLevels()
	{
		try {
			return LevelBusiness.getInstance().getLevels();
		} catch (SQLException e) {
		}
		return null;
	}
	
	public ArrayList<Schedule> getSchedule(int id)
	{
		try {
			return ScheduleBusiness.getInstance().getSchedulesByActivity(id);
		} catch (SQLException e) {
		}
		return null;
	}

}
