package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.model.Activity;
import progettoEsame.centropolisportivo.model.ActivityType;
import progettoEsame.centropolisportivo.model.CenterManager;
import progettoEsame.centropolisportivo.model.Room;
import progettoEsame.centropolisportivo.view.AddActivity;
import progettoEsame.centropolisportivo.view.Message;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;
public class AddActivityActionListener implements ActionListener {

	private AddActivity addActivityPanel;

	public AddActivityActionListener(AddActivity addActivityPanel) {
		this.addActivityPanel = addActivityPanel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(ADD_ACTIVITY_INSERT_BUTTON_ACTION_CMD))
		{
			ArrayList<String> newActivity = addActivityPanel.getActivityData();
			if(newActivity.get(3).equals("") || newActivity.get(4).equals("") || newActivity.get(5).equals("") || newActivity.get(0) == null || newActivity.get(2) == null)
			{
				addActivityPanel.removeMessageToPanel();
				addActivityPanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_ACTIVITY_INCOMPLETED_MSG));

			}
			else
			{
				try {
					Activity activity = new Activity();
					activity.setActivityType(ActivityType.findByType(newActivity.get(0)));
					activity.setCenterManager(CenterManager.findByEmail(newActivity.get(1)));
					activity.setRoom(Room.findByName(newActivity.get(2)));
					activity.setName(newActivity.get(3));
					activity.setDescription(newActivity.get(4));
					activity.setCost(Float.parseFloat(newActivity.get(5)));
					activity.setImagePath(newActivity.get(6));
					Activity.insert(activity);
					addActivityPanel.addMessageToPanel(Message.getInstance().printSuccessMsg(ADD_ACTIVITY_SUCCESS_MSG));
				} catch (SQLException e1) {
					e1.printStackTrace();
					addActivityPanel.removeMessageToPanel();
					addActivityPanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_ACTIVITY_DB_ERROR));
				}
			}
		}

	}

}
