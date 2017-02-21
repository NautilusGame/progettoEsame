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
			exception.printStackTrace();
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
			exception.printStackTrace();
		}
		event.setCost(Integer.parseInt(row[4]));
		event.setType(row[5]);
		event.setTrainer(TrainerDAO.getInstance().findByEmail(row[6]));
		event.setFree(!"0".equals(row[7]));

		return event;

	}

}
