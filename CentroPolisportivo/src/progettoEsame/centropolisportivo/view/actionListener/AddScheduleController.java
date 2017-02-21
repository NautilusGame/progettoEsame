package progettoEsame.centropolisportivo.view.actionListener;

import java.sql.SQLException;

import javax.swing.JLabel;

import progettoEsame.centropolisportivo.business.ActivityBusiness;
import progettoEsame.centropolisportivo.model.Activity;
import progettoEsame.centropolisportivo.view.AddSchedule;

public class AddScheduleController {
	
	private static AddScheduleController instance;
	private AddSchedule addSchedulePanel;
	public static synchronized AddScheduleController getInstance ()
	{
		if(instance==null)
			instance = new AddScheduleController();
		return instance;
	}
	
	public void init(AddSchedule addSchedulePanel,int activityId)
	{
		Activity tmpActivity = new Activity();
		try {
			tmpActivity = ActivityBusiness.getInstance().findByID(activityId);
			addSchedulePanel.setInitialString(tmpActivity.getName(),tmpActivity.getDescription(), tmpActivity.getRoom().getName(), tmpActivity.getActivityType().getType(),tmpActivity.getImagePath());
		} catch (SQLException e) {
			// TODO INVIA IL MESSAGGIO
			e.printStackTrace();
		}
		
	}

}
