package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import progettoEsame.centropolisportivo.dbConnection.DbConnection;
import progettoEsame.centropolisportivo.model.Schedule;

public class ScheduleDAO  {

	private static ScheduleDAO instance;

	public static synchronized ScheduleDAO getInstance ()
	{
		if(instance==null)
			instance = new ScheduleDAO();
		return instance;
	}

	public void insert(Schedule newSchedule) throws SQLException
	{
			String query = "INSERT INTO schedule( day, time, activity_id, trainer_email) VALUES ('"+newSchedule.getDay()+"','"+newSchedule.getTime()+"','"+newSchedule.getActivity().getId()+"','"+newSchedule.getTrainer().getEmail()+"');";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void delete(Integer id) throws SQLException
	{
		String query = "DELETE FROM schedule WHERE id = "+id+";";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void update(Schedule newSchedule) throws SQLException
	{
			String query = "UPDATE schedule SET day='"+newSchedule.getDay()+"',time='"+newSchedule.getTime()+"',activity_id='"+newSchedule.getActivity().getId()+"',trainer_email='"+newSchedule.getTrainer().getEmail()+"' ;";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}
	public Schedule findById(Integer id) throws SQLException
	{
		Schedule schedule = new Schedule();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM schedule WHERE  id= "+id+";");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		schedule.setId(Integer.parseInt(row[0]));
		schedule.setDay(row[1]);
		schedule.setTime(row[2]);
		schedule.setTrainer(TrainerDAO.getInstance().findByEmail(row[3]));
		schedule.setActivity(ActivityDAO.getInstance().findById(Integer.parseInt(row[4])));

		return schedule;

	}
	
	public ArrayList<Schedule> getAllScheduleByActivity(int idActivity)throws SQLException
	{
		String query = "SELECT * FROM schedule where activity_id="+idActivity;
		ArrayList<String[]> result = DbConnection.getInstance().eseguiQuery(query);
		ArrayList<Schedule> allSchedule = new ArrayList<Schedule>();
		
		if(result.size() == 0)
			return null;
		for(int i = 0;i<result.size();i++)
		{
			Schedule tmpSchedule = new Schedule();
			String[] row = result.get(i);
			tmpSchedule.setId(Integer.parseInt(row[0]));
			tmpSchedule.setDay(row[1]);
			tmpSchedule.setTime(row[2]);
			tmpSchedule.setTrainer(TrainerDAO.getInstance().findByEmail(row[3]));
			tmpSchedule.setActivity(ActivityDAO.getInstance().findById(Integer.parseInt(row[4])));
			allSchedule.add(tmpSchedule);
		}
		return allSchedule;
	}
	
	public Schedule getScheduleByDay(String day,String time,int idActivity)throws SQLException
	{
		String query = "SELECT * FROM schedule where activity_id="+idActivity+" AND day='"+day+"' AND time='"+time+"';";
		ArrayList<String[]> result = DbConnection.getInstance().eseguiQuery(query);
		Schedule schedule = new Schedule();
		
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		schedule.setId(Integer.parseInt(row[0]));
		schedule.setDay(row[1]);
		schedule.setTime(row[2]);
		schedule.setTrainer(TrainerDAO.getInstance().findByEmail(row[3]));
		schedule.setActivity(ActivityDAO.getInstance().findById(Integer.parseInt(row[4])));

		return schedule;
	}

}
