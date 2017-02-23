package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.view.AddActivity;
import progettoEsame.centropolisportivo.view.AddActivityType;
import progettoEsame.centropolisportivo.view.AddEvent;
import progettoEsame.centropolisportivo.view.AddNewLevel;
import progettoEsame.centropolisportivo.view.AddRoom;
import progettoEsame.centropolisportivo.view.RegistrationPanel;
import progettoEsame.centropolisportivo.view.TempAlertRegistrationQueue;
import progettoEsame.centropolisportivo.view.AcceptPaymentProposal;
import progettoEsame.centropolisportivo.view.AcceptRegistrationProposal;
import progettoEsame.centropolisportivo.view.Template;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;

public class TemplateActionListener implements ActionListener{

	private Template template;
	
	public TemplateActionListener (Template template){
		this.template=template;
	}
	
	public void actionPerformed(ActionEvent e){
	
		if(e.getActionCommand().equals(MEMBER_MENU_1))
		{
			this.template.removePage();
			RegistrationPanel rp = new RegistrationPanel();
			this.template.setPage(rp);
			this.template.revalidate();
			this.template.repaint();
		}
		else if(e.getActionCommand().equals(MEMBER_MENU_2))
		{
			
		}
		else if(e.getActionCommand().equals(MEMBER_MENU_3))
		{
			
		}
		else if(e.getActionCommand().equals(MEMBER_MENU_4))
		{
			
		}
		else if(e.getActionCommand().equals(MEMBER_MENU_5))
		{
			
		}
		else if(e.getActionCommand().equals(TRAINER_MENU_1))
		{
			this.template.removePage();
			AddEvent addEventPanel = new AddEvent();
			this.template.setPage(addEventPanel);
			this.template.revalidate();
			this.template.repaint();
		}
		else if(e.getActionCommand().equals(TRAINER_MENU_2))
		{
			
		}
		else if(e.getActionCommand().equals(TRAINER_MENU_3))
		{
			
		}
		else if(e.getActionCommand().equals(TRAINER_MENU_4))
		{
			
		}
		else if(e.getActionCommand().equals(TRAINER_MENU_5))
		{
			
		}
		else if(e.getActionCommand().equals(CENTERMANAGER_MENU_1))
		{
			this.template.removePage();
			AcceptRegistrationProposal registrationProposal=new AcceptRegistrationProposal();
			this.template.setPage(registrationProposal);
			this.template.revalidate();
			this.template.repaint();
		}
		else if(e.getActionCommand().equals(CENTERMANAGER_MENU_2))
		{
			this.template.removePage();
			AddActivity addActivityPanel = new AddActivity();
			this.template.setPage(addActivityPanel);
			this.template.revalidate();
			this.template.repaint();
		}
		else if(e.getActionCommand().equals(CENTERMANAGER_MENU_3))
		{

			this.template.removePage();
			AddNewLevel addNewLevelPanel = new AddNewLevel();
			this.template.setPage(addNewLevelPanel);
			this.template.revalidate();
			this.template.repaint();
		}
		else if(e.getActionCommand().equals(CENTERMANAGER_MENU_4))
		{
			this.template.removePage();
			AddRoom addRoomPanel = new AddRoom();
			this.template.setPage(addRoomPanel);
			this.template.revalidate();
			this.template.repaint();
		}
		else if(e.getActionCommand().equals(CENTERMANAGER_MENU_5))
		{
			this.template.removePage();
			AddActivityType addActivityType = new AddActivityType();
			this.template.setPage(addActivityType);
			this.template.revalidate();
			this.template.repaint();
		}

		else if(e.getActionCommand().equals(CENTERMANAGER_MENU_6))
		{
			this.template.removePage();
			AcceptPaymentProposal acceptPaymentProposal = new AcceptPaymentProposal();
			this.template.setPage(acceptPaymentProposal);
			this.template.revalidate();
			this.template.repaint();
		}
		

		else if(e.getActionCommand().equals(CENTERMANAGER_MENU_7))
		{
			this.template.removePage();
			TempAlertRegistrationQueue tarq = new TempAlertRegistrationQueue();
			this.template.setPage(tarq);
			this.template.revalidate();
			this.template.repaint();
		}
		
		
	}

}
