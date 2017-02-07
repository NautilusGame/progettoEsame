package progettoEsame.centropolisportivo.view.actionListener;

import javax.swing.JMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;

import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.view.LoginRegister;
import progettoEsame.centropolisportivo.view.MainFrame;
import progettoEsame.centropolisportivo.view.Template;

public class TemplateMenuListener implements MenuListener {

	private Template template;
	private MainFrame mf;
	
	public TemplateMenuListener (Template template, MainFrame mf){
		this.template=template;
		this.mf=mf;
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
			mf.remove(template);
			mf.add(new LoginRegister(this.mf));
			mf.setSize(501,500);//serve per far aggiornare correttamente il mainframe
			mf.repaint();			
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
