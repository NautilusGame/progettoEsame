package progettoEsame.centropolisportivo.view.actionListener;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.business.EventBusiness;
import progettoEsame.centropolisportivo.model.Event;
import progettoEsame.centropolisportivo.view.EventCard;
import progettoEsame.centropolisportivo.view.Template;

public class EventCardController {

	private static EventCardController instance;
	private static Template template;
	
	public static synchronized EventCardController getInstance()
	{
		if(instance == null)
			return new EventCardController();
		return instance;
	}
	
	public void setTemplate(Template template)
	{
		this.template=template;
	}
	
	public ArrayList<EventCard> getAllEventCard() throws SQLException
	{
		ArrayList<Event> tmpAllEvent=EventBusiness.getInstance().getAllEvent(); 
		ArrayList<EventCard> allEventCard = new ArrayList<EventCard>(); 
		ArrayList<String> valueEventPanel;
		int user;
		
		//user = 1: member; user = 2; trainer; user = 0; il resto
		if((SessionCheck.getInstance().getStatusSession())&&(SessionCheck.getInstance().getTypeUser().equals("member")))
			user=1;
		else if((SessionCheck.getInstance().getStatusSession())&&(SessionCheck.getInstance().getTypeUser().equals("trainer")))
			user = 2;
		else 
			user=0;
		
		if(tmpAllEvent!=null)
		{
			for(int i=0;i<tmpAllEvent.size();i++)
			{
				valueEventPanel = new ArrayList<>();
				valueEventPanel.add(String.valueOf(tmpAllEvent.get(i).getId()));
				valueEventPanel.add(tmpAllEvent.get(i).getName());
				valueEventPanel.add(tmpAllEvent.get(i).getStartDate().toString());
				valueEventPanel.add(String.valueOf(tmpAllEvent.get(i).getFinishDate().toString()));
				valueEventPanel.add(Double.valueOf(tmpAllEvent.get(i).getCost()).toString());
				valueEventPanel.add(tmpAllEvent.get(i).getType());
				valueEventPanel.add(Boolean.valueOf(tmpAllEvent.get(i).isFree()).toString());
				EventCard tmp=new EventCard(valueEventPanel, user,this.template);
				allEventCard.add(tmp);
			}
			return allEventCard;
		}
		else 
			return null;
	}
}
