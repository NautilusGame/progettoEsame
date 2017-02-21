package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.model.Activity;
import progettoEsame.centropolisportivo.model.Trainer;

public class TrainerBusiness {

	private static TrainerBusiness instance;

	
	public static synchronized TrainerBusiness getInstance()
	{
		if(instance == null)
		{
			return new TrainerBusiness();
		}
		return instance;
	}
	
	public Trainer findByEmail(String email) throws SQLException
	{
		return Trainer.findByEmail(email);
	}
}
