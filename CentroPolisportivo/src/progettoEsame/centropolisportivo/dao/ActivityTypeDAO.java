package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import progettoEsame.centropolisportivo.dbConnection.DbConnection;
import progettoEsame.centropolisportivo.model.ActivityType;

public class ActivityTypeDAO  {

	private static ActivityTypeDAO instance;

	public static synchronized ActivityTypeDAO getInstance ()
	{
		if(instance==null)
			instance = new ActivityTypeDAO();
		return instance;
	}

	public void insert(ActivityType newActivityType) throws SQLException
	{
			String query = "INSERT INTO activity_type(type, center_manager_email) VALUES"
					+ " ('"+newActivityType.getType()+"','"+newActivityType.getCenterManager().getEmail()+"');";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void delete(Integer id) throws SQLException
	{
		String query = "DELETE FROM activityType WHERE id = "+id+";";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void update(ActivityType newActivityType) throws SQLException
	{
			String query = "UPDATE activity_type SET type='"+newActivityType.getType()+"',  center_manager_email='"+newActivityType.getCenterManager().getEmail()+"';";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}
	
	public ActivityType findById(Integer id) throws SQLException
	{
		ActivityType activityType = new ActivityType();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM activity_type WHERE  id= "+id+";");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		activityType.setId(Integer.parseInt(row[0]));
		activityType.setType(row[1]);
		activityType.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[2]));
		return activityType;

	}
	

	public ActivityType findByType(String type) throws SQLException
	{
		ActivityType activityType = new ActivityType();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM activity_type WHERE  type= '"+type+"';");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		activityType.setId(Integer.parseInt(row[0]));
		activityType.setType(row[1]);
		activityType.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[2]));
		return activityType;

	}
	public ArrayList<ActivityType> getAllActivityType() throws SQLException
	{
		ArrayList<String[]> result = new ArrayList<>();
		ArrayList<ActivityType> activityTypeList = new ArrayList<>();
		String query = "SELECT * FROM activity_type";
		result = DbConnection.getInstance().eseguiQuery(query);
		if(result.size() == 0) return null;
		
		for(int i = 0;i<result.size();i++)
		{
			ActivityType tmpActivityType = new ActivityType();
			String[] row = result.get(i);
			tmpActivityType.setId(Integer.parseInt(row[0]));
			tmpActivityType.setType(row[1]);
			tmpActivityType.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[2]));
			activityTypeList.add(tmpActivityType);
		}
		return activityTypeList;
	}

}
