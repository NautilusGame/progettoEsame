package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.dao.ScheduleDAO;
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
	
	public ArrayList<ArrayList<String>> getScheduleByActivity(int idActivity) throws SQLException
	{
		ArrayList<Schedule> schedules= Schedule.getScheduleByActivity(idActivity);
		ArrayList<String> schedule;
		ArrayList<ArrayList<String>> allSchedulesValue= new ArrayList<>();
		
		if(schedules!=null)
		{
			for (int i=0; i<schedules.size(); i++)
			{
				schedule= new ArrayList<>();
				schedule.add(String.valueOf(schedules.get(i).getId()));//0
				schedule.add(String.valueOf(schedules.get(i).getDay()));//1
				schedule.add(String.valueOf(schedules.get(i).getTime()));//2
				schedule.add(String.valueOf(schedules.get(i).getTrainer().getName()));//3 nome dell 'istruttore se si volesse mostrare nella scehda della activity	
				allSchedulesValue.add(schedule);
			}
		}
		else return null;
		
		return allSchedulesValue;
	}
	
	public static Schedule getScheduleByDay(String day,String time,int idActivity) throws SQLException
	{
		return ScheduleDAO.getInstance().getScheduleByDay(day, time, idActivity);
	}

}
