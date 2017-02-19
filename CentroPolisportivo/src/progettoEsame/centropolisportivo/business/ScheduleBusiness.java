package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;

import progettoEsame.centropolisportivo.model.Schedule;

public class ScheduleBusiness {
	
	private static ScheduleBusiness instance;

	public static synchronized ScheduleBusiness getInstance()
	{
		if(instance == null)
			return new ScheduleBusiness();
		return instance;
	}
	
	public void insert(Schedule newSchedule) throws SQLException
	{
		Schedule.insert(newSchedule);
	}

}
