package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;

import java.util.ArrayList;
import progettoEsame.centropolisportivo.dbConnection.DbConnection;
import progettoEsame.centropolisportivo.model.Activity;


public class ActivityDAO {

	private static ActivityDAO instance;

	public static synchronized ActivityDAO getInstance ()
	{
		if(instance==null)
			instance = new ActivityDAO();
		return instance;
	}

	public void insert(Activity newActivity) throws SQLException
	{
			String query = "INSERT INTO activity( name, description, cost, room_id, center_manager_email, image_path, activity_type_id) VALUES"
					+ "('"+newActivity.getName()+"','"+newActivity.getDescription()+"',"+newActivity.getCost()+","
							+ newActivity.getRoom().getId()+",'"+newActivity.getCenterManager().getEmail()+"','"+newActivity.getImagePath()+"',"+newActivity.getActivityType().getId()+");";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void delete(Integer id) throws SQLException
	{
		String query = "DELETE FROM activity WHERE id = '"+id+"'";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void update(Activity newActivity) throws SQLException
	{
			String query = "UPDATE activity SET name = '"+newActivity.getName()+"',"
					+ " description = '"+newActivity.getDescription()+"',' cost = "+newActivity.getCost()+
					", room_id = '"+newActivity.getRoom().getId()+
					",  center_manager_email= "+newActivity.getCenterManager().getEmail()+",  image_path = '"+newActivity.getImagePath()+"',  activity_type_id = "+newActivity.getActivityType().getId()+");";

			DbConnection.getInstance().eseguiAggiornamento(query);
	}
	public Activity findById(Integer id) throws SQLException
	{
		Activity activity = new Activity();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM activity WHERE id= '"+id+"';");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		activity.setId(Integer.parseInt(row[0]));
		activity.setName(row[1]);
		activity.setDescription(row[2]);
		activity.setCost(Integer.parseInt(row[3]));
		activity.setRoom(RoomDAO.getInstance().findById(Integer.parseInt(row[4])));
		activity.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[5]));
		activity.setImagePath(row[6]);
		activity.setActivityType(ActivityTypeDAO.getInstance().findById(Integer.parseInt(row[7])));

		return activity;

	}

	public ArrayList<Activity> getAllActivity()throws SQLException
	{
		String query = "SELECT * FROM activity";
		ArrayList<String[]> result = DbConnection.getInstance().eseguiQuery(query);
		ArrayList<Activity> allActivity = new ArrayList<Activity>();
		
		if(result.size() == 0)
			return null;
		for(int i = 0;i<result.size();i++)
		{
			Activity tmpActivity = new Activity();
			String[] row = result.get(i);
			tmpActivity.setId(Integer.parseInt(row[0]));
			tmpActivity.setName(row[1]);
			tmpActivity.setDescription(row[2]);
			tmpActivity.setCost(Float.parseFloat(row[3]));
			tmpActivity.setImagePath(row[6]);
			tmpActivity.setActivityType(ActivityTypeDAO.getInstance().findById(Integer.parseInt(row[7])));
			allActivity.add(tmpActivity);
		}
		return allActivity;
	}
	
	public ArrayList<Activity> getAllRegisteredActivities(String memberEmail) throws SQLException
	{
		String query = "SELECT a.id FROM activity AS a,registration AS r WHERE a.id = r.activity_id AND r.member_email = '"+memberEmail+"' ;";
		ArrayList<String[]> result = DbConnection.getInstance().eseguiQuery(query);
		ArrayList<Activity> allActivity = new ArrayList<>();
		
		if(result.size() == 0)
			return null;
		for(int i = 0;i<result.size();i++)
		{
			allActivity.add(this.findById(Integer.parseInt(result.get(0)[0])));
		}
		return allActivity;
	}
}
