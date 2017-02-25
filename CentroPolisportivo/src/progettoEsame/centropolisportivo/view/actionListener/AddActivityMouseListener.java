package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import com.sun.glass.ui.Cursor;

import progettoEsame.centropolisportivo.view.AddActivity;
import progettoEsame.centropolisportivo.view.Message;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;

public class AddActivityMouseListener implements MouseListener {

	private AddActivity addActivityPanel;
	
	public AddActivityMouseListener(AddActivity addActivityPanel) {
		this.addActivityPanel = addActivityPanel;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().getClass().equals(JLabel.class))
		{
			JLabel label = (JLabel)e.getSource();
			if(label.getName().equals(ADD_ACTIVITY_IMAGE_ACTION_CMD))
			{
				String path;
				this.addActivityPanel.openFileChooser();
				path = addActivityPanel.getImagePath();
				if(path.equals(ADD_ACTIVITY_IMAGE_ERROR_MSG))
				{
					addActivityPanel.removeMessageToPanel();
					addActivityPanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_ACTIVITY_IMAGE_ERROR_MSG));
				}
				else if(path.equals(ADD_ACTIVITY_CANCEL_CHOOSE))
				{
					//do nothing
				}
				
				else
				{
					addActivityPanel.setPathToImageLabel(path);
					addActivityPanel.resetImage(path);
				}
				
			}
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
