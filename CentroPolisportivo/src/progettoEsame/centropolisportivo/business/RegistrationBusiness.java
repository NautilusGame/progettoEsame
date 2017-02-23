package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;

import progettoEsame.centropolisportivo.dao.RegistrationDAO;
import progettoEsame.centropolisportivo.exception.RegistrationException;
import progettoEsame.centropolisportivo.model.CenterManager;
import progettoEsame.centropolisportivo.model.Member;
import progettoEsame.centropolisportivo.model.Registration;
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
	
	public void insert(Registration newRegistration) throws SQLException
	{
		RegistrationDAO.getInstance().insert(newRegistration);
	}
	
	public ArrayList<Registration> getAllRegisteredRegistration(String memberEmail)throws SQLException
	{
		return Registration.getAllRegisteredRegistration(memberEmail);
	}
	
	public Registration findById(int id)throws SQLException
	{
		return Registration.findById(id);
	}

}
