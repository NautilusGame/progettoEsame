package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;

import progettoEsame.centropolisportivo.model.TempAlterRegistration;

public class TempAlterRegistrationBusiness {

	private static TempAlterRegistrationBusiness instance;


	public static synchronized TempAlterRegistrationBusiness getInstance()
	{
		if(instance == null)
		{
			return new TempAlterRegistrationBusiness();
		}
		return instance;
	}
	
	public int insert(TempAlterRegistration newTempAlterRegistration)throws SQLException
	{
		return TempAlterRegistration.insert(newTempAlterRegistration);
	}
	
	public TempAlterRegistration findById(int id)throws SQLException
	{
		return TempAlterRegistration.findById(id);
	}
}
