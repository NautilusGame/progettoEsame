package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import progettoEsame.centropolisportivo.view.ModifyRegistration;
import progettoEsame.centropolisportivo.view.RegistrationPanel;

public class RegistrationPanelActionListener implements ActionListener {

	private RegistrationPanel registrationPanel;
	
	public RegistrationPanelActionListener(RegistrationPanel registrationPanel)
	{
		this.registrationPanel = registrationPanel;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton modifyButton = (JButton)arg0.getSource();
		this.registrationPanel.makeInvisibleMainPanel(new ModifyRegistration(Integer.parseInt(modifyButton.getName())));

	}

}
