package progettoEsame.centropolisportivo.view.actionListener;

import static progettoEsame.centropolisportivo.view.ConstantClass.REGISTER_DATA_INCOMPLETED_MSG;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.view.ConstantClass;
import progettoEsame.centropolisportivo.view.MainFrame;
import progettoEsame.centropolisportivo.view.Message;
import progettoEsame.centropolisportivo.view.Settings;

public class SettingsActionListener implements ActionListener 
{

	private Settings settings;
	private MainFrame mf;
	
	public SettingsActionListener(Settings settings, MainFrame mf)
	{
		this.settings=settings;
		this.mf=mf;		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(ConstantClass.SHOW_PASSWORD_FIELD))
		{
			this.settings.showChangePassword();
		}
		else if(e.getActionCommand().equals(ConstantClass.UNDO_ACTION_PASSWORD))
		{
			this.settings.hiddenChangePassword();
		}
		else if(e.getActionCommand().equals(ConstantClass.SAVE_SETTINGS))
		{
			ArrayList<String> value=this.settings.getFormData();
			if((value.get(0).equals(""))||(value.get(1).equals(""))||(value.get(2).equals(""))||(value.get(3).equals(""))||(value.get(4).equals("")))
			{
				this.settings.removeMessageToPanel();
				this.settings.addMessageToPanel(Message.getInstance().printErrorMsg(REGISTER_DATA_INCOMPLETED_MSG));
			}
			else 
			{
				//controllo della pasword nuova se inserita correttamente
				if(!value.get(3).equals(value.get(4)))
				{
					this.settings.removeMessageToPanel();
					this.settings.addMessageToPanel(Message.getInstance().printErrorMsg(ConstantClass.REGISTER_PASSWORD_NOT_EQUALS_MSG));
				}
				
				//controllo che se è logato come trainer o Centermangaer il campo phone number sia compliato
				if(!Session.getInstance().getTypeUser().equals("member"))
				{
					if(value.get(5).equals(""))
					{
						this.settings.removeMessageToPanel();
						this.settings.addMessageToPanel(Message.getInstance().printErrorMsg(REGISTER_DATA_INCOMPLETED_MSG));
					}
				}	
				
				//TODO inserire gestioner per l'update dei fdati delle impostazioni
				
			}
		}
		
	}
}
