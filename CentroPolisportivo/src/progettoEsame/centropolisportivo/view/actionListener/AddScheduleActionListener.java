package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.business.ScheduleBusiness;
import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.model.Activity;
import progettoEsame.centropolisportivo.model.Schedule;
import progettoEsame.centropolisportivo.model.Trainer;
import progettoEsame.centropolisportivo.view.AddSchedule;
import progettoEsame.centropolisportivo.view.Message;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;
public class AddScheduleActionListener implements ActionListener{
	
	private AddSchedule addSchedulePanel;
	
	public AddScheduleActionListener(AddSchedule addSchedulePanel)
	{
		this.addSchedulePanel = addSchedulePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(ADD_SCHEDULE_INSERT_BUTTON_ATION_CMD))
		{
			ArrayList<String> selectedSchedule = addSchedulePanel.getSelectedSchedule();
			if(selectedSchedule == null || selectedSchedule.size() == 0)
			{
				addSchedulePanel.removeMessageToPanel();
				addSchedulePanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_SCHEDULE_INSERT_INCOMPLETE_FIELD));
			}
			else
			{
				for(int i = 0;i<selectedSchedule.size();i++)
				{
					ArrayList<String> tmpParser = this.parser(selectedSchedule.get(i));
					Schedule newSchedule = new Schedule();
					try {
						newSchedule.setActivity(Activity.findByID(addSchedulePanel.getActivityID()));
						newSchedule.setTrainer(Trainer.findByEmail(Session.getInstance().getEmail()));
						newSchedule.setDay(tmpParser.get(0));
						newSchedule.setTime(tmpParser.get(1));
						ScheduleBusiness.getInstance().insert(newSchedule);
						addSchedulePanel.removeMessageToPanel();
						addSchedulePanel.addMessageToPanel(Message.getInstance().printSuccessMsg(ADD_SCHEDULE_SUCCESS_MSG));
						
					} catch (SQLException | SessionException e1) {
						e1.printStackTrace();
						addSchedulePanel.removeMessageToPanel();
						addSchedulePanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_SCHEDULE_DB_ERROR));
					}
				}
			}
		}
		
	}
	
	private ArrayList<String> parser(String toParse)
	{
		ArrayList<String> parsed = new ArrayList<>();
		int indexOf = toParse.indexOf(" ");
		parsed.add((toParse.substring(0, indexOf)).replace(" ", ""));
		parsed.add((toParse.substring(indexOf+1, toParse.length())).replace(" ", ""));
		
		return parsed;
	}

}
