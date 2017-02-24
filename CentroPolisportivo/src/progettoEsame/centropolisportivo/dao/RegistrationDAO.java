package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import progettoEsame.centropolisportivo.dbConnection.DbConnection;
import progettoEsame.centropolisportivo.model.Activity;
import progettoEsame.centropolisportivo.model.Registration;


public class RegistrationDAO {

	private static RegistrationDAO instance;

	public static synchronized RegistrationDAO getInstance ()
	{
		if(instance==null)
			instance = new RegistrationDAO();
		return instance;
	}

	public int insert(Registration newRegistration) throws SQLException
	{
		if(newRegistration.getEvent()!=null)
		{
			String query = "INSERT INTO registration(date, cost, member_email,event_id) VALUES"
					+ "('"+newRegistration.getDate()+"',"+newRegistration.getCost()+",'"+newRegistration.getMember().getEmail()+"',"+newRegistration.getEvent().getId()+")";
			return DbConnection.getInstance().eseguiAggiornamento(query);
		}
		else
		{
			String query = "INSERT INTO registration(deadline, date, cost, member_email, activity_id, level_id) VALUES"
					+ "('"+newRegistration.getDeadline()+"','"+newRegistration.getDate()+"',"+newRegistration.getCost()+",'"+newRegistration.getMember().getEmail()+"',"+newRegistration.getActivity().getId()+","+newRegistration.getLevel().getId()+")";
			System.out.println(query);
			return DbConnection.getInstance().eseguiAggiornamento(query);
		}
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
				"', level_id = "+newRegistration.getLevel().getId()+", event_id = "+newRegistration.getEvent().getId()+", cost = "+newRegistration.getCost()+";";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}
	
	public void updateLevel(int idRegistration, int idLevel) throws SQLException
	{
		String query = "UPDATE registration SET level_id="+idLevel+" where id="+idRegistration;
		DbConnection.getInstance().eseguiAggiornamento(query);
	}
	public Registration findById(int id) throws SQLException
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
		registration.setCost(Double.parseDouble(row[3]));
		registration.setMember(MemberDAO.getInstance().findByEmail(row[4]));
		if(row[5] == null)
			registration.setActivity(null);
		else
			registration.setActivity(ActivityDAO.getInstance().findById(Integer.parseInt(row[5])));

		if(row[6]==null)
			registration.setEvent(null);
		else 
			registration.setEvent(EventDAO.getInstance().findById(Integer.parseInt(row[6])));
		if(row[7] ==null)
			registration.setLevel(null);
		else
			registration.setLevel(LevelDAO.getInstance().findById(Integer.parseInt(row[7])));
		return registration;

	}

	public Registration findByMemberAndActivity(String email,int idActivity) throws SQLException
	{
		Registration registration;
		registration =new Registration();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM registration WHERE activity_id= "+idActivity+" and member_email='"+email+"';");
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

		registration.setCost(Double.parseDouble(row[3]));
		registration.setMember(MemberDAO.getInstance().findByEmail(row[4]));
		registration.setActivity(ActivityDAO.getInstance().findById(Integer.parseInt(row[5])));

		if(row[6]==null)
			registration.setEvent(null);
		else 
			registration.setEvent(EventDAO.getInstance().findById(Integer.parseInt(row[6])));

		registration.setLevel(LevelDAO.getInstance().findById(Integer.parseInt(row[7])));
		return registration;

	}

	public ArrayList<Registration> getAllRegisteredRegistration(String memberEmail) throws SQLException
	{
		String query = "SELECT r.id FROM activity AS a,registration AS r WHERE a.id = r.activity_id AND r.member_email = '"+memberEmail+"' ;";
		ArrayList<String[]> result = DbConnection.getInstance().eseguiQuery(query);
		ArrayList<Registration> allRegistration = new ArrayList<>();

		if(result.size() == 0)
		{
			return null;
		}
		for(int i = 0;i<result.size();i++)
		{
			allRegistration.add(this.findById(Integer.parseInt(result.get(0)[0])));
		}

		return allRegistration;
	}




}
