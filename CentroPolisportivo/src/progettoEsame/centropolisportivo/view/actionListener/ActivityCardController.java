package progettoEsame.centropolisportivo.view.actionListener;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import progettoEsame.centropolisportivo.business.ActivityBusiness;
import progettoEsame.centropolisportivo.model.Activity;
import progettoEsame.centropolisportivo.view.ActivityCard;
import progettoEsame.centropolisportivo.view.Template;

public class ActivityCardController
{

	private static ActivityCardController instance;
	private static Template template;
	private int user;
	
	public static synchronized ActivityCardController getInstance()
	{
		if(instance == null)
			return new ActivityCardController();
		return instance;
	}
	
	public void setTemplate(Template template)
	{
		this.template=template;
	}
	
	public ArrayList<ActivityCard> getAllActivityCard() throws SQLException
	{
		ArrayList<Activity> tmpAllActivity=ActivityBusiness.getInstance().getAllActivity();
		ArrayList<ActivityCard> allActivityCard = new ArrayList<ActivityCard>();
		ArrayList<String> valueActivityPanel;
		
		System.out.println(SessionCheck.getInstance().getStatusSession());
		//user = 1: member; user = 2; trainer; user = 0; il resto
		if((SessionCheck.getInstance().getStatusSession())&&(SessionCheck.getInstance().getTypeUser().equals("member")))
			this.user=1;
		else if((SessionCheck.getInstance().getStatusSession())&&(SessionCheck.getInstance().getTypeUser().equals("trainer")))
			this.user = 2;
		else 
			this.user=0;
		
			
		
		if(tmpAllActivity!=null)
		{
			for(int i=0;i<tmpAllActivity.size();i++)
			{
				valueActivityPanel = new ArrayList<>();
				valueActivityPanel.add(String.valueOf(tmpAllActivity.get(i).getId()));
				valueActivityPanel.add(tmpAllActivity.get(i).getName());
				valueActivityPanel.add(tmpAllActivity.get(i).getDescription());
				valueActivityPanel.add(String.valueOf(tmpAllActivity.get(i).getCost()));
				valueActivityPanel.add(tmpAllActivity.get(i).getImagePath());
				ActivityCard tmp=new ActivityCard(valueActivityPanel, user,this.template);
				allActivityCard.add(tmp);
			}
			return allActivityCard;
		}
		else 
			return null;
	}
}
