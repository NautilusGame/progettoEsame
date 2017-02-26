package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;


import progettoEsame.centropolisportivo.business.LevelBusiness;
import progettoEsame.centropolisportivo.business.MemberBusiness;
import progettoEsame.centropolisportivo.business.RegistrationBusiness;
import progettoEsame.centropolisportivo.business.RegistrationCalendarBusiness;
import progettoEsame.centropolisportivo.business.ScheduleBusiness;
import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.business.TempAlterRegistrationBusiness;
import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.model.RegistrationCalendar;
import progettoEsame.centropolisportivo.model.Schedule;
import progettoEsame.centropolisportivo.model.TempAlterRegistration;
import progettoEsame.centropolisportivo.view.Message;
import progettoEsame.centropolisportivo.view.ModifyRegistration;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;
public class ModifyRegistrationActionListener implements ActionListener {

	private ModifyRegistration modifyRegistrationPanel;
	private int idTempAlterRegistration;
	public ModifyRegistrationActionListener(ModifyRegistration modifyRegistrationPanel) {
		
		this.modifyRegistrationPanel = modifyRegistrationPanel;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {

		String levelName = modifyRegistrationPanel.getLevelName();
		int id = modifyRegistrationPanel.getId();
		TempAlterRegistration tar = new TempAlterRegistration();
		try {
			tar.setRegistration(RegistrationBusiness.getInstance().findById(id));
			tar.setMember(MemberBusiness.getInstance().findByEmail(Session.getInstance().getEmail()));
			tar.setConfirmed(0);
			tar.setLevel(LevelBusiness.getInstance().findLevelByName(levelName));
			this.idTempAlterRegistration = TempAlterRegistrationBusiness.getInstance().insert(tar);
		} catch (SQLException | SessionException e) {
		}
		ArrayList<String> schedule = modifyRegistrationPanel.getSelectedSchedule();
		if(schedule == null)
		{
			modifyRegistrationPanel.addMessageToPanel(Message.getInstance().printErrorMsg(MODIFY_PANEL_NO_SCHEDULE));
		}
		else
		{
			for(int i = 0;i<schedule.size();i++)
			{
				ArrayList<String> tmpParser = this.parser(schedule.get(i));
				Schedule newSchedule = new Schedule();
				RegistrationCalendar newRegistrationCalendar = new RegistrationCalendar();
				try {
					newSchedule = ScheduleBusiness.getScheduleByDay(tmpParser.get(0), tmpParser.get(1), RegistrationBusiness.getInstance().findById(id).getActivity().getId());
					newRegistrationCalendar.setSchedule(newSchedule);
					newRegistrationCalendar.setRegistration(RegistrationBusiness.getInstance().findById(id));
					newRegistrationCalendar.setTempAlterRegistration(TempAlterRegistrationBusiness.getInstance().findById(this.idTempAlterRegistration));
					RegistrationCalendarBusiness.getInstance().insert(newRegistrationCalendar);	
					modifyRegistrationPanel.removeMessageToPanel();
					modifyRegistrationPanel.addMessageToPanel(Message.getInstance().printSuccessMsg(MODIFY_PANEL_SUCCESS));
					
					
				} catch (SQLException e) {
					modifyRegistrationPanel.removeMessageToPanel();
					modifyRegistrationPanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_SCHEDULE_DB_ERROR));
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
