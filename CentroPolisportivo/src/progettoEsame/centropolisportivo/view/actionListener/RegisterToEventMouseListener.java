package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import progettoEsame.centropolisportivo.view.Message;
import progettoEsame.centropolisportivo.view.RegisterToEvent;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;
public class RegisterToEventMouseListener implements MouseListener {

	private RegisterToEvent registerToCompetitionPanel;
	public  RegisterToEventMouseListener(RegisterToEvent registerToCompetitionPanel) {
		this.registerToCompetitionPanel = registerToCompetitionPanel;
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		registerToCompetitionPanel.openFileChooser();
		String path = registerToCompetitionPanel.getCsrcPath();
		if(path.equals(REGISTER_TO_COMPETITION_FILE_ERROR_MSG))
		{
			registerToCompetitionPanel.removeMessageToPanel();
			registerToCompetitionPanel.addMessageToPanel(Message.getInstance().printErrorMsg(REGISTER_TO_COMPETITION_FILE_ERROR_MSG));
		}
		else if(path.equals(REGISTER_TO_COMPETITION_CANCEL_CHOOSE))
		{
			// do nothing
		}
		else
		{
			registerToCompetitionPanel.removeMessageToPanel();
			registerToCompetitionPanel.setPathToCSRCLabel(path);
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
