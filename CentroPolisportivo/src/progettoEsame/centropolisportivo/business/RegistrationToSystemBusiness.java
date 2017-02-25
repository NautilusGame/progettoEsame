package progettoEsame.centropolisportivo.business;

import static progettoEsame.centropolisportivo.business.ConstantClass.EMAIL_REGISTRATION_ERROR_MSG;

import java.sql.SQLException;

import progettoEsame.centropolisportivo.exception.RegistrationException;
import progettoEsame.centropolisportivo.model.CenterManager;
import progettoEsame.centropolisportivo.model.Member;
import progettoEsame.centropolisportivo.model.Trainer;

public class RegistrationToSystemBusiness {
	
	private static RegistrationToSystemBusiness instance;

	public static synchronized RegistrationToSystemBusiness getInstance()
	{
		if(instance == null)
			return new RegistrationToSystemBusiness();
		return instance;
	}
	
	public Boolean checkMemberDataRegistration(Member member)throws RegistrationException,SQLException
	{
		if(this.checkIfOtherEmailExist(member.getEmail()))
		{
			throw new RegistrationException(EMAIL_REGISTRATION_ERROR_MSG);
		}
		member.setPassword(MD5.getMD5(member.getPassword()));
		Member.insert(member);
		Session.getInstance().createSession(member.getEmail(),"member");
		return true;
	}

	public Boolean checkTrainerDataRegistration(Trainer trainer)throws RegistrationException,SQLException
	{
		if(this.checkIfOtherEmailExist(trainer.getEmail()))
		{
			throw new RegistrationException(EMAIL_REGISTRATION_ERROR_MSG);
		}
		trainer.setPassword(MD5.getMD5(trainer.getPassword()));
		Trainer.insert(trainer);
		Session.getInstance().createSession(trainer.getEmail(),"trainer");
		return true;
	}
	
	private boolean checkIfOtherEmailExist(String email)throws SQLException
	{
		Trainer tmpTrainer = Trainer.findByEmail(email);
		Member tmpMember = Member.findByEmail(email);
		CenterManager tmpCenterManager = CenterManager.findByEmail(email);
		if(tmpTrainer != null)
			return true;
		if(tmpMember != null)
			return true;
		if(tmpCenterManager != null)
			return true;
		return false;
	}

}
