package progettoEsame.centropolisportivo.business;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.model.CenterManager;
import progettoEsame.centropolisportivo.model.Member;
import progettoEsame.centropolisportivo.model.Trainer;

public class SettingsBusiness 
{
	private static SettingsBusiness instance;

	public static synchronized SettingsBusiness getInstance()
	{
		if(instance == null)
			return new SettingsBusiness();
		return instance;
	}

	public Boolean updateMemberSettings(ArrayList<String> memberSettings, String oldPassword) throws SQLException, SessionException
	{
		Member member=Member.findByEmail(Session.getInstance().getEmail());
		Member updateMember= new Member();
		String md5Password;
		
		updateMember.setName(memberSettings.get(1));
		updateMember.setSurname(memberSettings.get(0));
		updateMember.setBirthday(Date.valueOf(memberSettings.get(6)));
		updateMember.setConfirmed(1);
		updateMember.setEmail(member.getEmail());
		updateMember.setCenterManager(member.getCenterManager());
		
		if(oldPassword.equals(""))//se la strigna oldPaswrod è vuota non si è aggiornata la password
		{
			updateMember.setPassword(member.getPassword());					
			Member.update(updateMember);
			return true;
		}
		else
		{
			md5Password = MD5.getMD5(oldPassword);
			if (!member.getPassword().equals(md5Password))//si verifica che lapassword inserita è uguale a quella presente
			{
				return false;//se la password corrent e la vecchia
			}
			else 
			{
				updateMember.setPassword(MD5.getMD5(memberSettings.get(3)));						
				Member.update(updateMember);
				return true;
			}
		}
	}

	//funzioni per l'aggiornamento dei dati dell'utente loggato
	//TODO fare l'implementazione dei metodi 
	public Boolean updateTrainerSettings(ArrayList<String> trainerSettings, String oldPassword) throws SQLException, SessionException
	{
		Trainer trainer=Trainer.findByEmail(Session.getInstance().getEmail());
		Trainer updateTrainer= new Trainer();
		String md5Password;
		
		updateTrainer.setName(trainerSettings.get(1));
		updateTrainer.setSurname(trainerSettings.get(0));
		updateTrainer.setPhoneNumber(trainerSettings.get(5));
		updateTrainer.setConfirmed(1);
		updateTrainer.setEmail(trainer.getEmail());
		updateTrainer.setCenterManager(trainer.getCenterManager());
		
		if(oldPassword.equals(""))//se la strigna oldPaswrod è vuota non si è aggiornata la password
		{
			updateTrainer.setPassword(trainer.getPassword());					
			Trainer.update(updateTrainer);
			return true;
		}
		else
		{
			md5Password = MD5.getMD5(oldPassword);
			if (!trainer.getPassword().equals(md5Password))//si verifica che lapassword inserita è uguale a quella presente
			{
				return false;//se la password corrent e la vecchia
			}
			else 
			{
				updateTrainer.setPassword(MD5.getMD5(trainerSettings.get(3)));						
				Trainer.update(updateTrainer);
				return true;
			}
		}
	}
	
	public Boolean updateCenterManagerSettings(ArrayList<String> centerManagerSettings, String oldPassword) throws SQLException, SessionException
	{
		CenterManager centerManager=CenterManager.findByEmail(Session.getInstance().getEmail());
		CenterManager updateCenterManager= new CenterManager();
		
		updateCenterManager.setName(centerManagerSettings.get(1));
		updateCenterManager.setSurname(centerManagerSettings.get(0));
		updateCenterManager.setPhoneNumber(centerManagerSettings.get(5));
		updateCenterManager.setEmail(centerManager.getEmail());
		
		if(oldPassword.equals(""))//se la strigna oldPaswrod è vuota non si è aggiornata la password
		{
			updateCenterManager.setPassword(centerManager.getPassword());					
			CenterManager.update(updateCenterManager);
			return true;
		}
		else
		{
			if (!centerManager.getPassword().equals(oldPassword))//si verifica che lapassword inserita è uguale a quella presente
			{
				return false;//se la password corrent e la vecchia
			}
			else 
			{
				updateCenterManager.setPassword(centerManagerSettings.get(3));						
				CenterManager.update(updateCenterManager);
				return true;
			}
		}
	}
	
	
	/**********************************************************************/
	
	
	//funzioni per avere i dati dell'utente loggato
	public ArrayList<String> getMemberSettings() throws SQLException, SessionException
	{
		Member member=Member.findByEmail(Session.getInstance().getEmail());
		ArrayList<String> userData = new ArrayList<>();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String data=df.format(member.getBirthday());
		userData.add(member.getName()); //0
		userData.add(member.getSurname());  //1
		userData.add(data);  //2
		
		return userData;
	}
	
	public  ArrayList<String> getTrainerSettings() throws SQLException, SessionException
	{
		Trainer trainer=Trainer.findByEmail(Session.getInstance().getEmail());
		ArrayList<String> userData = new ArrayList<>();
	
		userData.add(trainer.getName()); //0
		userData.add(trainer.getSurname());  //1
		userData.add(trainer.getPhoneNumber());  //2
		
		return userData;
	}
	
	public  ArrayList<String> getCenterManagerSettings() throws SQLException, SessionException
	{
		CenterManager centerManager=CenterManager.findByEmail(Session.getInstance().getEmail());
		ArrayList<String> userData = new ArrayList<>();
		
		userData.add(centerManager.getName()); //0
		userData.add(centerManager.getSurname());  //1
		userData.add(centerManager.getPhoneNumber());  //2
		
		return userData;
	}



}
