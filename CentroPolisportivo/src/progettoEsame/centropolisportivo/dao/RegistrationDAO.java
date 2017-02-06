package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import progettoEsame.centropolisportivo.dbConnection.DbConnection;
import progettoEsame.centropolisportivo.model.Registration;


public class RegistrationDAO {

	private static RegistrationDAO instance;

	public static synchronized RegistrationDAO getInstance ()
	{
		if(instance==null)
			instance = new RegistrationDAO();
		return instance;
	}

	public void insert(Registration newRegistration) throws SQLException
	{
			String query = "INSERT INTO registration(deadline, date, member_email, schedule_id, activity_id, event_id) VALUES"
					+ "("+newRegistration.getId()+",'"+newRegistration.getDeadline()+"','"+newRegistration.getDate()+"','"+newRegistration.getMember().getEmail()+"',"
							+ ""+newRegistration.getSchedule().getId()+","+newRegistration.getActivity().getId()+","+newRegistration.getEvent().getId()+")";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void delete(Integer id) throws SQLException
	{
		String query = "DELETE FROM registration WHERE id = '"+id+"'";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void update(Registration newRegistration) throws SQLException
	{
			String query = "UPDATE registration  deadline = '"+newRegistration.getDeadline()+"',"
					+ " date = '"+newRegistration.getDate()+"',' member_mail = '"+newRegistration.getMember().getEmail()+
					"', schedule_id = '"+newRegistration.getSchedule().getId()+"', event_id = "+newRegistration.getEvent().getId()+";";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}
	public Registration findById(Integer id) throws SQLException
	{
		Registration registration;
		registration =new Registration();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM registration WHERE id= '"+id+"';");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		registration.setId(Integer.parseInt(row[0]));
		String deadLine = row[1];
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate = dateFormat.parse(deadLine);
			registration.setDeadline(new java.sql.Date(myDate.getTime()));
		}
		catch(ParseException exception)
		{
			exception.printStackTrace();
		}
		
		String date = row[2];
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate = dateFormat.parse(date);
			registration.setDate(new java.sql.Date(myDate.getTime()));
		}
		catch(ParseException exception)
		{
			exception.printStackTrace();
		}
		registration.setMember(MemberDAO.getInstance().findByEmail(row[3]));
		registration.setSchedule(ScheduleDAO.getInstance().findById(Integer.parseInt(row[4])));
		registration.setActivity(ActivityDAO.getInstance().findById(Integer.parseInt(row[5])));
		registration.setEvent(EventDAO.getInstance().findById(Integer.parseInt(row[5])));
		return registration;

	}

}
