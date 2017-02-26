package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import progettoEsame.centropolisportivo.dbConnection.DbConnection;
import progettoEsame.centropolisportivo.model.Event;


public class EventDAO {

	private static EventDAO instance;

	public static synchronized EventDAO getInstance ()
	{
		if(instance==null)
			instance = new EventDAO();
		return instance;
	}

	public void insert(Event newEvent) throws SQLException
	{
		String query = "INSERT INTO event(name, start_date, finish_date, cost, type, trainer_email, free) VALUES"
				+ "('"+newEvent.getName()+"','"+newEvent.getStartDate()+"','"+newEvent.getFinishDate()+"',"
				+ newEvent.getCost()+",'"+newEvent.getType()+"','"+newEvent.getTrainer().getEmail()+"',"+newEvent.isFree()+");";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void delete(Integer id) throws SQLException
	{
		String query = "DELETE FROM event WHERE id = '"+id+"'";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void update(Event newEvent) throws SQLException
	{
		String query = "UPDATE event SET name='"+newEvent.getName()+"',start_date="+newEvent.getStartDate()+",finish_date="+newEvent.getFinishDate()+",cost="+newEvent.getCost()+",type='"+newEvent.getType()+"',trainer_email='"+newEvent.getTrainer().getEmail()+"',free="+newEvent.isFree()+";";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}
	public Event findById(Integer id) throws SQLException
	{
		Event event = new Event();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM event WHERE id= '"+id+"';");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		event.setId(Integer.parseInt(row[0]));
		event.setName(row[1]);
		String startDate = row[2];
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate = dateFormat.parse(startDate);
			event.setStartDate(new java.sql.Date(myDate.getTime()));
		}
		catch(ParseException exception)
		{
		}
		String finishDate = row[3];
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate = dateFormat.parse(finishDate);
			event.setFinishDate(new java.sql.Date(myDate.getTime()));
		}
		catch(ParseException exception)
		{
		}
		event.setCost(Integer.parseInt(row[4]));
		event.setType(row[5]);
		event.setTrainer(TrainerDAO.getInstance().findByEmail(row[6]));
		event.setFree(!"0".equals(row[7]));

		return event;

	}

	public ArrayList<Event> getAllEvent() throws SQLException
	{
		ArrayList<Event> events = new ArrayList<>();
		String query = "SELECT * FROM event";
		ArrayList<String[]> draftEvents = DbConnection.getInstance().eseguiQuery(query);
		if(draftEvents == null) return null;
		for(int i = 0;i<draftEvents.size();i++)
		{
			Event tmpEvent = new Event();
			String[] row = draftEvents.get(i); 
			tmpEvent.setId(Integer.parseInt(row[0])); 
			tmpEvent.setName(row[1]);
			String startDate = row[2];
			try
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date myDate = dateFormat.parse(startDate);
				tmpEvent.setStartDate(new java.sql.Date(myDate.getTime()));
			}
			catch(ParseException exception)
			{
			}
			String finishDate = row[3];
			try
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date myDate = dateFormat.parse(finishDate);
				tmpEvent.setFinishDate(new java.sql.Date(myDate.getTime()));
			}
			catch(ParseException exception)
			{
			}
			tmpEvent.setCost(Integer.parseInt(row[4]));
			tmpEvent.setType(row[5]);
			tmpEvent.setTrainer(TrainerDAO.getInstance().findByEmail(row[6]));
			tmpEvent.setFree(!"0".equals(row[7]));
			events.add(tmpEvent);
		}
		return events;
	}
	
	public ArrayList<Event> getAllRegisteredActivities(String memberEmail) throws SQLException
	{
		String query = "SELECT e.id FROM event AS e,registration AS r WHERE e.id = r.activity_id AND r.member_email = '"+memberEmail+"' ;";
		ArrayList<String[]> result = DbConnection.getInstance().eseguiQuery(query);
		ArrayList<Event> allEvent = new ArrayList<>();
		
		if(result.size() == 0)
			return null;
		for(int i = 0;i<result.size();i++)
		{
			allEvent.add(this.findById(Integer.parseInt(result.get(0)[0])));
		}
		return allEvent;
	}

}
