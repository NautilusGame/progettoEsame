package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;

import javax.swing.JLabel;

import progettoEsame.centropolisportivo.exception.RegistrationException;
import progettoEsame.centropolisportivo.model.Member;
import progettoEsame.centropolisportivo.model.Trainer;

import static progettoEsame.centropolisportivo.business.ConstantClass.*;
public class RegistrationBusiness {

	private static RegistrationBusiness instance;

	public static synchronized RegistrationBusiness getInstance()
	{
		if(instance == null)
			return new RegistrationBusiness();
		return instance;
	}

	public Boolean checkMemberDataRegistration(Member member)throws RegistrationException,SQLException
	{
		Member tmpmember = Member.findByEmail(member.getEmail());
		if(tmpmember !=null)
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
		Trainer tmpmember = Trainer.findByEmail(trainer.getEmail());
		if(tmpmember !=null)
		{
			throw new RegistrationException(EMAIL_REGISTRATION_ERROR_MSG);
		}
		Trainer.insert(trainer);
		Session.getInstance().createSession(trainer.getEmail(),"trainer");
		return true;
	}

}
