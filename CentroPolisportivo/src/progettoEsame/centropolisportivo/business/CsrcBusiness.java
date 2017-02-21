package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;

import progettoEsame.centropolisportivo.model.Csrc;

public class CsrcBusiness {

	private static CsrcBusiness instance;

	public static synchronized CsrcBusiness getInstance()
	{
		if(instance == null)
			return new CsrcBusiness();
		return instance;
	}
	
	public void insert(Csrc newCsrc) throws SQLException
	{
		Csrc.insert(newCsrc);
	}
	
}
