package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;

import progettoEsame.centropolisportivo.model.Member;

public class MemberBusiness {
	
	private static MemberBusiness instance;

	public static synchronized MemberBusiness getInstance()
	{
		if(instance == null)
			return new MemberBusiness();
		return instance;
	}
	
	public Member findByEmail(String email) throws SQLException
	{
		return Member.findByEmail(email);
	}

}
