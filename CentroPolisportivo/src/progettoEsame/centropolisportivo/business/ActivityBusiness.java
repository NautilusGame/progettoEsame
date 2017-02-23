package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.dao.ActivityDAO;
import progettoEsame.centropolisportivo.model.Activity;

public class ActivityBusiness
{
	private static ActivityBusiness instance;
	private ArrayList<Activity> allActivity;
	private Activity activity;

	
	public static synchronized ActivityBusiness getInstance()
	{
		if(instance == null)
		{
			return new ActivityBusiness();
		}
		return instance;
	}
	
	public ArrayList<Activity> getAllActivity()throws SQLException
	{
		this.allActivity = Activity.getAllActivity();
		return this.allActivity;
		
	}
	
	public Activity findByID(int activityID) throws SQLException
	{
		return Activity.findByID(activityID);
	}
	public ArrayList<String> getActivityById (int idActivity) throws SQLException
	{
		Activity tmpActivity=Activity.findByID(idActivity);
		ArrayList<String> valueActivity = new ArrayList<>();
		valueActivity.add(String.valueOf(tmpActivity.getId()));//0
		valueActivity.add(tmpActivity.getName());//1
		valueActivity.add(tmpActivity.getDescription());//2
		valueActivity.add(String.valueOf(tmpActivity.getCost()));//3
		valueActivity.add(tmpActivity.getImagePath());//4
		
		return valueActivity;
	}
	

	public ArrayList<Activity> getAllRegisteredActivities(String memberEmail) throws SQLException
	{
		return Activity.getAllRegisteredActivities(memberEmail);
	}


}
