package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;

import progettoEsame.centropolisportivo.dao.EventDAO;
import progettoEsame.centropolisportivo.model.Event;

public class EventBusiness {

private static EventBusiness instance;

	
	public static synchronized EventBusiness getInstance()
	{
		if(instance == null)
		{
			return new EventBusiness();
		}
		return instance;
	}
	
	public void insert(Event newEvent) throws SQLException
	{
		Event.insert(newEvent);
	}
	
	public Event findById(int id) throws SQLException
	{
		return EventDAO.getInstance().findById(id);
	}
	
}
