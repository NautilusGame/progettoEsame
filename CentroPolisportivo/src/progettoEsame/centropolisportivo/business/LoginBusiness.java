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
			if (member.getPassword().equals(md5Password)){
				Session.getInstance().createSession(email,"member");
			}
			else throw new LoginException(ConstantClass.ERROR_DIFFERENT_PASSWORD);
		}
		else throw new LoginException(ConstantClass.USERNAME_NOTFOUND);

		return true;
	}
	
	
	public Boolean LoginTrainer(String email, String password) throws LoginException,SQLException {	

		Trainer trainer = Trainer.findByEmail(email);
		String md5Password = MD5.getMD5(password);	
		
		if(trainer!=null)
		{
			if (trainer.getPassword().equals(md5Password)){
				Session.getInstance().createSession(email,"trainer");
			}
			else throw new LoginException(ConstantClass.ERROR_DIFFERENT_PASSWORD);
		}
		else throw new LoginException(ConstantClass.USERNAME_NOTFOUND);

		return true;
	}
	

	public Boolean LoginCenterManager(String email, String password) throws LoginException,SQLException {	

		CenterManager centerManager = CenterManager.findByEmail(email);
		String md5Password = MD5.getMD5(password);	
		
		if(centerManager!=null)
		{
			if (centerManager.getPassword().equals(md5Password)){
				Session.getInstance().createSession(email,"centerManager");
			}
			else throw new LoginException(ConstantClass.ERROR_DIFFERENT_PASSWORD);
		}
		else throw new LoginException(ConstantClass.USERNAME_NOTFOUND);

		return true;
	}



}
