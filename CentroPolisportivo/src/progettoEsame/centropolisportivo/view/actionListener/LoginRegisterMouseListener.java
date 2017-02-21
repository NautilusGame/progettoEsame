package progettoEsame.centropolisportivo.view.actionListener;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import progettoEsame.centropolisportivo.view.LoginRegister;
public class LoginRegisterMouseListener implements MouseListener{

	private LoginRegister lrview;

	public LoginRegisterMouseListener(LoginRegister lrview) {
		this.lrview = lrview;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if(e.getSource().getClass().equals(JLabel.class))
		{
			JLabel label = (JLabel)e.getSource();
			if(label.getName().equals(REGISTER_BUTTON_NAME))
			{
				lrview.removeMessageToPanel();
				lrview.makeLoginPanelInvisible();
				lrview.makeRegisterPanelVisible();
			}
			else if(label.getName().equals(LOGIN_BUTTON_NAME))
			{
				lrview.removeMessageToPanel();
				lrview.makeRegisterPanelInvisible();
				lrview.makeLoginPanelVisible();
			}
			}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		{
			if(e.getSource().getClass().equals(JLabel.class))
			{
				JLabel label = (JLabel)e.getSource();
				if(label.getName().equals("RegisterLabel"))
				{
					Cursor c = Cursor.getDefaultCursor();
					c = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
					this.lrview.setCursor(c);
				}
			}
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
