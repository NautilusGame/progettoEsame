package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import progettoEsame.centropolisportivo.business.Session;
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
			
		}
		else if(e.getActionCommand().equals(CENTERMANAGER_MENU_2))
		{
			
		}
		else if(e.getActionCommand().equals(CENTERMANAGER_MENU_3))
		{
			
		}
		else if(e.getActionCommand().equals(CENTERMANAGER_MENU_4))
		{
			
		}
		else if(e.getActionCommand().equals(CENTERMANAGER_MENU_5))
		{
			
		}
		
		
	}

}
