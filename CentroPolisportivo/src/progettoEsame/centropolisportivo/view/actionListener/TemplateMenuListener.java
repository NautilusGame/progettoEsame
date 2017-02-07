package progettoEsame.centropolisportivo.view.actionListener;

import javax.swing.JMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;

import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.view.Template;

public class TemplateMenuListener implements MenuListener {

	private Template template;

	public TemplateMenuListener (Template template){
		this.template=template;
	}
	
	@Override
	public void menuSelected(MenuEvent e)
	{
		JMenu menu=(JMenu)e.getSource();
		
		if(menu.getActionCommand().equals(PROFILE_ACTION_CMD))
		{
			System.out.println("Profilo");
		}
		else if(menu.getActionCommand().equals(FLYER_ACTION_CMD))
		{
			
		}
		else if(menu.getActionCommand().equals(LOGOUT_ACTION_CMD))
		{
			Session.getInstance().destroySession();
			//TODO inserire richiamo a panello dei login
		}
		else if(menu.getActionCommand().equals(HOME_ACTION_CMD))
		{
			
		}
		else if(menu.getActionCommand().equals(INFO_ACTION_CMD))
		{
			
		}
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
