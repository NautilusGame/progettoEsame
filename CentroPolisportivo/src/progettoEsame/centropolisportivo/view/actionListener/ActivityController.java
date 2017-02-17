package progettoEsame.centropolisportivo.view.actionListener;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import progettoEsame.centropolisportivo.business.ActivityBusiness;
import progettoEsame.centropolisportivo.model.Activity;
import progettoEsame.centropolisportivo.view.ActivityCard;

public class ActivityController
{

	private static ActivityController instance;

	public static synchronized ActivityController getInstance()
	{
		if(instance == null)
			return new ActivityController();
		return instance;
	}

	public ArrayList<ActivityCard> getAllActivityCard() throws SQLException
	{
		ArrayList<Activity> tmpAllActivity=ActivityBusiness.getInstance().getAllActivity();
		ArrayList<ActivityCard> allActivityCard = new ArrayList<ActivityCard>();
		ArrayList<String> valueActivityPanel = new ArrayList<>();
		int user;
		
		if(SessionCheck.getInstance().getStatusSession())
			user=1;
		else 
			user=0;
		
		if(tmpAllActivity!=null)
		{
			for(int i=0;i<tmpAllActivity.size();i++)
			{
				valueActivityPanel.add(String.valueOf(tmpAllActivity.get(i).getId()));
				valueActivityPanel.add(tmpAllActivity.get(i).getName());
				valueActivityPanel.add(tmpAllActivity.get(i).getDescription());
				valueActivityPanel.add(String.valueOf(tmpAllActivity.get(i).getCost()));
				valueActivityPanel.add(tmpAllActivity.get(i).getImagePath());
				ActivityCard tmp=new ActivityCard(valueActivityPanel, user);
				allActivityCard.add(tmp);
			}
			return allActivityCard;
		}
		else 
			return null;
	}
}
