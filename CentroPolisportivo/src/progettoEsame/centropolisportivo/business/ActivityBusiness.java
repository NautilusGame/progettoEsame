package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.model.Activity;

public class ActivityBusiness
{
	private static ActivityBusiness instance;
	private ArrayList<Activity> allActivity;

	
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

}
