package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import progettoEsame.centropolisportivo.view.ConstantClass;

public class ActivityCardActionListener implements ActionListener
{
	
	public ActivityCardActionListener()
	{
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals(ConstantClass.ACTIVITY_REGISTER_BUTTON))
		{
			//TODO richimamo della Activity scheda corretta
		}		
	}

}
