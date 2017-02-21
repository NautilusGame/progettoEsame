package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.dao.CenterManagerDAO;
import progettoEsame.centropolisportivo.model.CenterManager;
import progettoEsame.centropolisportivo.model.Member;
import progettoEsame.centropolisportivo.model.Trainer;

public class CenterManagerBusiness {

	private static CenterManagerBusiness instance;
	
	public static synchronized CenterManagerBusiness getInstance()
	{
		if(instance == null)
		{
			return new CenterManagerBusiness();
		}
		return instance;
	}
	
	public CenterManager findByEmail(String email) throws SQLException
	{
		return CenterManagerDAO.getInstance().findByEmail(email);
	}
}
