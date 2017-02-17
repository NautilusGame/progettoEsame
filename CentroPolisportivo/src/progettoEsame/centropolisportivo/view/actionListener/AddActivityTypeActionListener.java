package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.model.ActivityType;
import progettoEsame.centropolisportivo.model.CenterManager;
import progettoEsame.centropolisportivo.view.AddActivityType;
import progettoEsame.centropolisportivo.view.Message;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;

public class AddActivityTypeActionListener implements ActionListener {

	private AddActivityType addActivityTypePanel;
	
	public AddActivityTypeActionListener(AddActivityType addActivityTypePanel)
	{
		this.addActivityTypePanel = addActivityTypePanel;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String activityTypeName = addActivityTypePanel.getActivityTypeDate();
		if(activityTypeName.equals(""))
		{
			addActivityTypePanel.removeMessageToPanel();
			addActivityTypePanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_ACTIVITY_TYPE_INCOMPLETE_FILED));
		}
		else
		{
			try {
				ActivityType newActivityType = new ActivityType();
				newActivityType.setCenterManager(CenterManager.findByEmail(Session.getInstance().getEmail()));
				newActivityType.setType(activityTypeName);
				addActivityTypePanel.removeMessageToPanel();
				addActivityTypePanel.addMessageToPanel(Message.getInstance().printSuccessMsg(ADD_ACTIVITY_TYPE_SUCCESS_MSG));
			} catch (SQLException | SessionException e) {
				addActivityTypePanel.removeMessageToPanel();
				addActivityTypePanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_ACTIVITY_TYPE_DB_ERROR));
				e.printStackTrace();
			}
		}

	}

}
