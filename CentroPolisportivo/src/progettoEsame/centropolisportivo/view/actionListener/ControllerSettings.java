package progettoEsame.centropolisportivo.view.actionListener;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.business.SettingsBusiness;
import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.model.Member;

public class ControllerSettings 
{
	private static ControllerSettings instance;
	private static ArrayList<String> valueField;
	
	public static synchronized ControllerSettings getInstance()
	{
		if(instance == null)
			return new ControllerSettings();
		return instance;
	}
	
	public ArrayList<String> getValueFromMember() throws SQLException, SessionException
	{
		return SettingsBusiness.getInstance().getMemberSettings();		
	}
	
	public ArrayList<String> getValueFromTrainer() throws SQLException, SessionException
	{
		return SettingsBusiness.getInstance().getTrainerSettings();		
	}
	
	public ArrayList<String> getValueFromCenterManager() throws SQLException, SessionException
	{
		return SettingsBusiness.getInstance().getCenterManagerSettings();		
	}

}
