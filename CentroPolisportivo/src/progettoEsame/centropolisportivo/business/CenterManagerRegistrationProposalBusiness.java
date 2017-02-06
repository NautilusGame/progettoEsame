package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.model.Member;
import progettoEsame.centropolisportivo.model.Trainer;

public class CenterManagerRegistrationProposalBusiness {
	
	private static CenterManagerRegistrationProposalBusiness instance;
	private ArrayList<Member> unconfirmedMember;
	private ArrayList<Trainer> unconfirmedTrainer;
	
	public static synchronized CenterManagerRegistrationProposalBusiness getInstance()
	{
		if(instance == null)
		{
			return new CenterManagerRegistrationProposalBusiness();
		}
		return instance;
	}
	
	public ArrayList<Member> getAllUnconfirmedMember()throws SQLException
	{
		this.unconfirmedMember = Member.getAllUnconfirmedMember();
		return this.unconfirmedMember;
		
	}
	
	public ArrayList<Trainer> getAllUnconfirmedTrainer()throws SQLException
	{
		this.unconfirmedTrainer = Trainer.getAllUnconfirmedTrainer();
		return this.unconfirmedTrainer;
		
	}
	public int getNumberOfUnconfirmedUser()
	{
		return (this.unconfirmedMember.size() + this.unconfirmedTrainer.size());
	}

}
