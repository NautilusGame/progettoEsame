package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.view.AddActivity;
import progettoEsame.centropolisportivo.view.AddActivityType;
import progettoEsame.centropolisportivo.view.AddNewLevel;
import progettoEsame.centropolisportivo.view.AddRoom;
import progettoEsame.centropolisportivo.view.CenterManagerRegistrationProposal;
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
			//Inserisci qui la nuova pagina
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
			/*Prova p=new Prova();
			System.out.println("Okay");
			this.template.setPage(p);*/
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
			CenterManagerRegistrationProposal registrationProposal=new CenterManagerRegistrationProposal();
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
		
		
	}

}
