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
			String query = "INSERT INTO registration_calendar(schedule_id, registration_id,temp_alter_registration_id) VALUES"
					+ "("+newRegistrationCalendar.getSchedule().getId()+","+newRegistrationCalendar.getRegistration().getId()+","+newRegistrationCalendar.getTempAlterRegistration().getId()+");";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}
	
	public void delete(Integer id) throws SQLException
	{
		String query = "DELETE FROM registration_calendar WHERE id = "+id+"";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}
	
	public void deleteByTempAlteRegistration(Integer id) throws SQLException
	{
		String query = "DELETE FROM registration_calendar WHERE temp_alter_registration_id = "+id;
		DbConnection.getInstance().eseguiAggiornamento(query);
	}
	
	public void deleteByRegistration(Integer id) throws SQLException
	{
		String query = "DELETE FROM registration_calendar WHERE registration_id ="+id+" and temp_alter_registration_id IS NULL";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}
	
	public void update(RegistrationCalendar newRegistrationCalendar) throws SQLException
	{
			String query = "UPDATE registration_calendar SET schedule_id = "+newRegistrationCalendar.getSchedule().getId()+","
					+ " registration_id = "+newRegistrationCalendar.getRegistration().getId()+",temp_alter_registration_id = "+newRegistrationCalendar.getTempAlterRegistration().getId()+");";

			DbConnection.getInstance().eseguiAggiornamento(query);
	}
	
	public void updateForeignKeyTempAlter(int id) throws SQLException
	{
			String query = "UPDATE registration_calendar SET temp_alter_registration_id = null where registration_id="+id;

			DbConnection.getInstance().eseguiAggiornamento(query);
	}
	
	public RegistrationCalendar findById(Integer id) throws SQLException
	{
		RegistrationCalendar registrationCalendar = new RegistrationCalendar();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM registration_calendar WHERE id= "+id+";");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		registrationCalendar.setId(Integer.parseInt(row[0]));
		registrationCalendar.setSchedule(Schedule.findById(Integer.parseInt(row[1])));
		registrationCalendar.setRegistration(Registration.findById(Integer.parseInt(row[2])));
		registrationCalendar.setTempAlterRegistration(TempAlterRegistrationDAO.getInstance().findById(Integer.parseInt(row[3])));
		return registrationCalendar;

	}
	
	public ArrayList<RegistrationCalendar> findCalendarByTempAlterRegistrations(Integer id) throws SQLException
	{
		String query = "SELECT * FROM registration_calendar WHERE temp_alter_registration_id= "+id;
		ArrayList<String[]> result = DbConnection.getInstance().eseguiQuery(query);
		ArrayList<RegistrationCalendar> allCalendarByTempAlterRegistration = new ArrayList<RegistrationCalendar>();
		
		if(result.size() == 0)
			return null;
		for(int i = 0;i<result.size();i++)
		{
			RegistrationCalendar registrationCalendar = new RegistrationCalendar();
			String[] row = result.get(i);
			registrationCalendar.setId(Integer.parseInt(row[0]));
			registrationCalendar.setSchedule(Schedule.findById(Integer.parseInt(row[1])));
			registrationCalendar.setRegistration(Registration.findById(Integer.parseInt(row[2])));
			registrationCalendar.setTempAlterRegistration(TempAlterRegistrationDAO.getInstance().findById(Integer.parseInt(row[3])));
			allCalendarByTempAlterRegistration.add(registrationCalendar);
		}
		return allCalendarByTempAlterRegistration;
	}
	
	public ArrayList<RegistrationCalendar> findCalendarByRegistrations(Integer id) throws SQLException
	{
		String query = "SELECT * FROM registration_calendar WHERE registration_id= "+id+" and temp_alter_registration_id IS NULL";
		System.out.println(query);
		ArrayList<String[]> result = DbConnection.getInstance().eseguiQuery(query);
		ArrayList<RegistrationCalendar> allCalendarByTempAlterRegistration = new ArrayList<RegistrationCalendar>();
		
		if(result.size() == 0)
			return null;
		for(int i = 0;i<result.size();i++)
		{
			RegistrationCalendar registrationCalendar = new RegistrationCalendar();
			String[] row = result.get(i);
			registrationCalendar.setId(Integer.parseInt(row[0]));
			
			registrationCalendar.setSchedule(Schedule.findById(Integer.parseInt(row[1])));
			registrationCalendar.setRegistration(Registration.findById(Integer.parseInt(row[2])));			
			//registrationCalendar.setTempAlterRegistration(TempAlterRegistrationDAO.getInstance().findById(Integer.parseInt(row[3])));
			registrationCalendar.setTempAlterRegistration(null);
			allCalendarByTempAlterRegistration.add(registrationCalendar);
		}
		return allCalendarByTempAlterRegistration;
	}
}
