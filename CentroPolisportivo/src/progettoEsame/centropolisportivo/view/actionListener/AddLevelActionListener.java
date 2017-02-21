package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import progettoEsame.centropolisportivo.business.LevelBusiness;
import progettoEsame.centropolisportivo.exception.LevelException;
import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.model.Level;
import progettoEsame.centropolisportivo.view.AddNewLevel;
import progettoEsame.centropolisportivo.view.Message;

public class AddLevelActionListener implements ActionListener {

	private AddNewLevel addNewLevelPanel;
	
	public AddLevelActionListener(AddNewLevel addNewLevelPanel) 
	{
		this.addNewLevelPanel = addNewLevelPanel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		Level newLevel = addNewLevelPanel.getLevelData();
		if(newLevel.getName().equals(""))
		{
			addNewLevelPanel.addMessageToPanel(Message.getInstance().printErrorMsg("You have to insert level's name"));
		}
		else
		{
			try {
				LevelBusiness.getInstance().checkLevelData(newLevel);
				addNewLevelPanel.addMessageToPanel(Message.getInstance().printSuccessMsg("Level is addedd correctly"));
			} catch (LevelException | SQLException | SessionException e1) {
				addNewLevelPanel.addMessageToPanel(Message.getInstance().printErrorMsg(e1.getMessage()));
			}
		}
	}

}
