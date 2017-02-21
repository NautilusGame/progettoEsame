package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.dbConnection.DbConnection;
import progettoEsame.centropolisportivo.model.Activity;
import progettoEsame.centropolisportivo.model.Registration;
import progettoEsame.centropolisportivo.model.RegistrationCalendar;
import progettoEsame.centropolisportivo.model.Schedule;

public class RegistrationCalendarDAO {

	private static RegistrationCalendarDAO instance;

	public static synchronized RegistrationCalendarDAO getInstance ()
	{
		if(instance==null)
			instance = new RegistrationCalendarDAO();
		return instance;
	}
	
	public void insert(RegistrationCalendar newRegistrationCalendar) throws SQLException
	{
			String query = "INSERT INTO registration_calendar(schedule_id, registration_id) VALUES"
					+ "("+newRegistrationCalendar.getSchedule().getId()+","+newRegistrationCalendar.getRegistration().getId()+");";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}
	
	public void delete(Integer id) throws SQLException
	{
		String query = "DELETE FROM registration_calendar WHERE id = "+id+"";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}
	
	public void update(RegistrationCalendar newRegistrationCalendar) throws SQLException
	{
			String query = "UPDATE registration_calendar SET schedule_id = "+newRegistrationCalendar.getSchedule().getId()+","
					+ " registration_id = "+newRegistrationCalendar.getRegistration().getId()+");";

			DbConnection.getInstance().eseguiAggiornamento(query);
	}
	
	public RegistrationCalendar findById(Integer id) throws SQLException
	{
		RegistrationCalendar registrationCalendar = new RegistrationCalendar();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM registration_calendar WHERE id= '"+id+"';");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		registrationCalendar.setId(Integer.parseInt(row[0]));
		registrationCalendar.setSchedule(Schedule.findById(Integer.parseInt(row[1])));
		registrationCalendar.setRegistration(Registration.findById(Integer.parseInt(row[2])));

		return registrationCalendar;

	}
}
