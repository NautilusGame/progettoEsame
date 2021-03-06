package progettoEsame.centropolisportivo.view.actionListener;

import static progettoEsame.centropolisportivo.view.ConstantClass.REGISTER_DATA_INCOMPLETED_MSG;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.business.SettingsBusiness;
import progettoEsame.centropolisportivo.view.ConstantClass;
import progettoEsame.centropolisportivo.view.Message;
import progettoEsame.centropolisportivo.view.Settings;


public class SettingsActionListener implements ActionListener 
{

	private Settings settings;
	private boolean checkField;
	private boolean checkPassword;
	
	public SettingsActionListener(Settings settings)
	{
		this.settings=settings;
		this.checkField=true;
		this.checkPassword=true;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
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
			
			if((value.get(0).equals(""))||(value.get(1).equals("")))
			{
				this.settings.removeMessageToPanel();
				this.settings.addMessageToPanel(Message.getInstance().printErrorMsg(REGISTER_DATA_INCOMPLETED_MSG));
				this.checkField=false;
			}
			
			if(this.settings.isVisiblePassword())
			{
				if((value.get(2).equals(""))||(value.get(3).equals(""))||(value.get(4).equals("")))
				{
					this.settings.removeMessageToPanel();
					this.settings.addMessageToPanel(Message.getInstance().printErrorMsg(REGISTER_DATA_INCOMPLETED_MSG));
					this.checkPassword=false;
				}
				else if(!value.get(3).equals(value.get(4)))//controllo della pasword nuova se inserita correttamente
				{
					this.settings.removeMessageToPanel();
					this.settings.addMessageToPanel(Message.getInstance().printErrorMsg(ConstantClass.REGISTER_PASSWORD_NOT_EQUALS_MSG));
					this.checkPassword=false;
				}
				else this.checkPassword=true;				
			}
			
			
			if((Session.getInstance().getTypeUser().equals("trainer"))&&(this.checkField)&&(this.checkPassword))//controllo che se � logato come trainer o Centermangaer il campo phone number sia compliato
			{
				if(value.get(5).equals(""))
				{
					this.settings.addMessageToPanel(Message.getInstance().printErrorMsg(REGISTER_DATA_INCOMPLETED_MSG));
				}
				else 
				{
					try
					{
						if(this.settings.isVisiblePassword())//si verifica se sono state modificate le impostazioni dlla password
						{	
							if(SettingsBusiness.getInstance().updateTrainerSettings(value,value.get(2)))
							{
								this.settings.removeMessageToPanel();
								this.settings.addMessageToPanel(Message.getInstance().printSuccessMsg(ConstantClass.SUCCESS_UPDATE));
							}
							else
							{
								this.settings.removeMessageToPanel();
								this.settings.addMessageToPanel(Message.getInstance().printErrorMsg(ConstantClass.ERROR_UPDATE));
							}
						}
						else 
						{
							if(SettingsBusiness.getInstance().updateTrainerSettings(value,""))
							{
								this.settings.removeMessageToPanel();
								this.settings.addMessageToPanel(Message.getInstance().printSuccessMsg(ConstantClass.SUCCESS_UPDATE));
							}
							else
							{
								this.settings.removeMessageToPanel();
								this.settings.addMessageToPanel(Message.getInstance().printErrorMsg(ConstantClass.ERROR_UPDATE));
							}
						}	
					}
					catch(Exception ex)
					{
					//TODO inserire stampa del messaggio di errore
					}
				}
			}
			else if((Session.getInstance().getTypeUser().equals("member"))&&(this.checkField)&&(this.checkPassword))
			{
				try
				{
					if(this.settings.isVisiblePassword())//si verifica se sono state modificate le impostazioni dlla password
					{	
						if(SettingsBusiness.getInstance().updateMemberSettings(value,value.get(2)))
						{
							this.settings.removeMessageToPanel();
							this.settings.addMessageToPanel(Message.getInstance().printSuccessMsg(ConstantClass.SUCCESS_UPDATE));
						}
						else
						{
							this.settings.removeMessageToPanel();
							this.settings.addMessageToPanel(Message.getInstance().printErrorMsg(ConstantClass.ERROR_UPDATE));
						}
					}
					else 
					{
						if(SettingsBusiness.getInstance().updateMemberSettings(value,""))
						{
							this.settings.removeMessageToPanel();
							this.settings.addMessageToPanel(Message.getInstance().printSuccessMsg(ConstantClass.SUCCESS_UPDATE));
						}
						else
						{
							this.settings.removeMessageToPanel();
							this.settings.addMessageToPanel(Message.getInstance().printErrorMsg(ConstantClass.ERROR_UPDATE));
						}
					}	
				}
				catch(Exception ex)
				{
					//TODO inserire stampa del messaggio di errore
				}
			}	
			else if((Session.getInstance().getTypeUser().equals("centerManager"))&&(this.checkField)&&(this.checkPassword))
			{
				if(value.get(5).equals(""))
				{
					this.settings.addMessageToPanel(Message.getInstance().printErrorMsg(REGISTER_DATA_INCOMPLETED_MSG));
				}
				else 
				{
					try
					{
						if(this.settings.isVisiblePassword())//si verifica se sono state modificate le impostazioni dlla password
						{	
							if(SettingsBusiness.getInstance().updateCenterManagerSettings(value,value.get(2)))
							{
								this.settings.removeMessageToPanel();
								this.settings.addMessageToPanel(Message.getInstance().printSuccessMsg(ConstantClass.SUCCESS_UPDATE));
							}
							else
							{
								this.settings.removeMessageToPanel();
								this.settings.addMessageToPanel(Message.getInstance().printErrorMsg(ConstantClass.ERROR_UPDATE));
							}
							
						}
						else 
						{
							if(SettingsBusiness.getInstance().updateCenterManagerSettings(value,""))
							{
								this.settings.removeMessageToPanel();
								this.settings.addMessageToPanel(Message.getInstance().printSuccessMsg(ConstantClass.SUCCESS_UPDATE));
								//TODO mettere colore verde
							}
							else
							{
								this.settings.removeMessageToPanel();
								this.settings.addMessageToPanel(Message.getInstance().printErrorMsg(ConstantClass.ERROR_UPDATE));
							}							
						}	
					}
					catch(Exception ex)
					{
						
					}
				}
			}				
		}	
	}
}
