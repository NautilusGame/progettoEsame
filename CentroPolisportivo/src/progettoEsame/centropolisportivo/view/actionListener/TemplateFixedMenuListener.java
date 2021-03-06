package progettoEsame.centropolisportivo.view.actionListener;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.view.Documentation;
import progettoEsame.centropolisportivo.view.Flyer;
import progettoEsame.centropolisportivo.view.LoginRegister;
import progettoEsame.centropolisportivo.view.MainFrame;
import progettoEsame.centropolisportivo.view.Settings;
import progettoEsame.centropolisportivo.view.Template;

public class TemplateFixedMenuListener implements ActionListener {

	private Template template;
	private MainFrame mf;
	
	
	public TemplateFixedMenuListener (Template template,MainFrame mf){
		this.template=template;
		this.mf=mf;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals(PROFILE_ACTION_CMD))
		{
			this.template.removePage();
			Settings s = new Settings(this.mf);
			this.template.setPage(s);
			this.template.revalidate();
			this.template.repaint();
		}
		else if(e.getActionCommand().equals(FLYER_ACTION_CMD))
		{
			this.template.removePage();
			Flyer f=new Flyer(this.template);
			this.template.setPage(f);
			this.template.revalidate();
			this.template.repaint();
		}
		else if(e.getActionCommand().equals(LOGOUT_ACTION_CMD))
		{
			Session.getInstance().destroySession();
			this.mf.remove(template);
			this.mf.add(new Template(this.mf));
			this.mf.revalidate();
			this.mf.repaint();
			
			//TODO inserire richiamo a panello dei login
		}
		else if(e.getActionCommand().equals(INFO_ACTION_CMD))
		{
			this.template.removePage();
			Documentation doc=new Documentation();
			this.template.setPage(doc);
			this.template.revalidate();
			this.template.repaint();
		}
		else if(e.getActionCommand().equals(LOGIN_REGISTER_BUTTON))
		{
			this.mf.remove(template);
			this.mf.add(new LoginRegister(this.mf));
			this.mf.revalidate();
			this.mf.repaint();
		}
	}


}
