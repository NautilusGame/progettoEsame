package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import progettoEsame.centropolisportivo.business.ConfirmAlterRegistrationBusiness;
import progettoEsame.centropolisportivo.model.TempAlterRegistration;
import progettoEsame.centropolisportivo.view.ConfirmAlterRegistrationCard;
import progettoEsame.centropolisportivo.view.ConstantClass;

public class ConfirmAlterRegistrationActionListener implements ActionListener 
{
	private ConfirmAlterRegistrationCard card;
	private TempAlterRegistration tar;
	
	public ConfirmAlterRegistrationActionListener(ConfirmAlterRegistrationCard card,TempAlterRegistration tar)
	{
		this.card=card;
		this.tar=tar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals(ConstantClass.ACCEPT_BUTTON_CMD))
		{
			try
			{
				ConfirmAlterRegistrationBusiness.getInstance().updateRegistration(this.tar.getRegistration().getId(), this.tar.getLevel().getId());
			}
			catch (Exception ex) {
				ex.printStackTrace();
				// TODO: handle exception
			}
		}
		else if(e.getActionCommand().equals(ConstantClass.DENY_BUTTON_CMD))
		{
			System.out.println(this.card.getId());
			try
			{
				ConfirmAlterRegistrationBusiness.getInstance().deleteAlterCalendar(this.card.getId());
				ConfirmAlterRegistrationBusiness.getInstance().deleteTempAlterRegistration(this.card.getId());
				
			}
			catch (Exception ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		}
		
	}
	

}
