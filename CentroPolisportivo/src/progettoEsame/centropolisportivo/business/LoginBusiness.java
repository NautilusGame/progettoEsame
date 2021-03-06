package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;

import progettoEsame.centropolisportivo.business.ConstantClass;
import progettoEsame.centropolisportivo.exception.LoginException;
import progettoEsame.centropolisportivo.model.CenterManager;
import progettoEsame.centropolisportivo.model.Member;
import progettoEsame.centropolisportivo.model.Trainer;

public class LoginBusiness {

	private static LoginBusiness instance;
	
	public static LoginBusiness getInstance() {
		if(instance == null)
			instance = new LoginBusiness();
		return instance;
	}
	
	public Boolean LoginMember(String email, String password) throws LoginException,SQLException {	

		Member member = Member.findByEmail(email);
		String md5Password = MD5.getMD5(password);	
		
		if(member!=null)
		{
			if ((member.getPassword().equals(md5Password)))
			{
				if(member.getConfirmed()==1)
					Session.getInstance().createSession(email,"member");
				else if(member.getConfirmed() == 0)
					throw new LoginException(ConstantClass.WAIT_TO_BE_ACCEPTED);
				else
					throw new LoginException(ConstantClass.YOU_ARE_REFUSED);
			}
			else 
				throw new LoginException(ConstantClass.ERROR_DIFFERENT_PASSWORD);
		}
		else return false;

		return true;
	}
	
	
	public Boolean LoginTrainer(String email, String password) throws LoginException,SQLException {	

		Trainer trainer = Trainer.findByEmail(email);
		String md5Password = MD5.getMD5(password);	
		
		if(trainer!=null)
		{
			if (trainer.getPassword().equals(md5Password))
			{
				if(trainer.isConfirmed()==1)
					Session.getInstance().createSession(email,"trainer");
				else if(trainer.isConfirmed() == 0)
					throw new LoginException(ConstantClass.WAIT_TO_BE_ACCEPTED);
				else
					throw new LoginException(ConstantClass.YOU_ARE_REFUSED);
			}
			else throw new LoginException(ConstantClass.ERROR_DIFFERENT_PASSWORD);
		}
		else return false;

		return true;
	}
	

	public Boolean LoginCenterManager(String email, String password) throws LoginException,SQLException {	

		CenterManager centerManager = CenterManager.findByEmail(email);
		//String md5Password = MD5.getMD5(password);	
		
		if(centerManager!=null)
		{
			if (centerManager.getPassword().equals(password))
			{
				Session.getInstance().createSession(email,"centerManager");
			}
			else throw new LoginException(ConstantClass.ERROR_DIFFERENT_PASSWORD);
		}
		else return false;

		return true;
	}



}
