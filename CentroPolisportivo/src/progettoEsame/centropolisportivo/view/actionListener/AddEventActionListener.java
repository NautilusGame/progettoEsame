package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JCheckBox;

import progettoEsame.centropolisportivo.business.EventBusiness;
import progettoEsame.centropolisportivo.model.Event;
import progettoEsame.centropolisportivo.view.AddEvent;
import progettoEsame.centropolisportivo.view.Message;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;

public class AddEventActionListener implements ActionListener {

	private AddEvent addEventPanel;

	public AddEventActionListener(AddEvent addEventPanel) {
		this.addEventPanel = addEventPanel;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals(ADD_EVENT_IS_FREE_EVENT_ACTION_CMD))
		{
			JCheckBox tmpCheckBox = (JCheckBox) arg0.getSource();
			if(tmpCheckBox.isSelected())
			{
				addEventPanel.makePriceInvisible();
			}
			else
			{
				addEventPanel.makePriceVisible();
			}
		}
		else if(arg0.getActionCommand().equals(ADD_EVENT_INSERT_BUTTON_EVENT_ACTION_CMD))
		{
			String cost = addEventPanel.getCost();
			Event newEvent = addEventPanel.getEventData();
			Date tmpFinishDate = newEvent.getFinishDate();
			Date tmpStartDate = newEvent.getStartDate();
			if(newEvent.getName().equals(""))
			{
				addEventPanel.removeMessageToPanel();
				addEventPanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_EVENT_INCOMPLETE_FIELD));
			}
			else if(!this.isNumeric(cost) && !newEvent.isFree())
			{
				addEventPanel.removeMessageToPanel();
				addEventPanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_EVENT_PRICE_STING));
			}
			else if(cost.equals("") && !newEvent.isFree())
			{
				addEventPanel.removeMessageToPanel();
				addEventPanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_EVENT_INCOMPLETE_FIELD));
			}
			else if(!newEvent.isFree() && Integer.parseInt(cost)<0)
			{
				addEventPanel.removeMessageToPanel();
				addEventPanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_EVENT_PRICE_NEGATIVE));
			}
			else if(tmpStartDate.compareTo(new Date(new java.util.Date().getTime()))<0)
			{
				addEventPanel.removeMessageToPanel();
				addEventPanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_EVENT_PREVIOUS_DATE));	
			}
			else if(tmpFinishDate.before(tmpStartDate))
			{
				addEventPanel.removeMessageToPanel();
				addEventPanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_EVENT_PREVIOUS_DATE_2));
			}
			else
			{
				if(!newEvent.isFree())
				{
					newEvent.setCost(Integer.parseInt(cost));
				}
				else
				{
					newEvent.setCost(0);
				}
				try {
					EventBusiness.getInstance().insert(newEvent);
					addEventPanel.removeMessageToPanel();
					addEventPanel.addMessageToPanel(Message.getInstance().printSuccessMsg(ADD_EVENT_SUCCESS_MSG));
				} catch (SQLException e) {
					e.printStackTrace();
					addEventPanel.removeMessageToPanel();
					addEventPanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_EVENT_DB_ERROR));
				}
			}
		}

	}
	private boolean isNumeric(String s) {  
		return s.matches("[-+]?\\d*\\.?\\d+");  
	}  

}
