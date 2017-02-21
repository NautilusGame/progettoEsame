package progettoEsame.centropolisportivo.business;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.model.Activity;
import progettoEsame.centropolisportivo.model.Level;
import progettoEsame.centropolisportivo.model.Member;
import progettoEsame.centropolisportivo.model.Registration;

public class RegistrationActivityBusiness 
{
private static RegistrationActivityBusiness instance;
	
	public static RegistrationActivityBusiness getInstance() 
	{
		if(instance == null)
			instance = new RegistrationActivityBusiness();
		return instance;
	}
	
	public boolean memberJoinedActivity (String emailMember, int idActivity) throws SQLException //funzion per vedere se l'utente è registato almeno una volta alla activity selezionata 
	{
		Registration tmp=Registration.findByMemberAndActivity(emailMember, idActivity);

		
		if(tmp!=null)
			return true;		
		else 
			return false;
	}
	
	public boolean insertNewRegistration(ArrayList<ArrayList<String>> valueArray)
	{
		ArrayList<String> newRegistration=valueArray.get(1);
		ArrayList<String> schedules=valueArray.get(0);
		Registration tmp=new Registration();
		
		tmp.setActivity(new Activity(Integer.parseInt(newRegistration.get(3))));
		tmp.setDate(java.sql.Date.valueOf(newRegistration.get(2)));
		tmp.setDeadline(java.sql.Date.valueOf(newRegistration.get(4)));
		tmp.setMember(new Member(newRegistration.get(0)));
		
		try
		{
			tmp.setLevel(Level.findByName(newRegistration.get(1)));			
			tmp.setCost(Double.parseDouble((ActivityBusiness.getInstance().getActivityById(Integer.parseInt(newRegistration.get(3))).get(3))));
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try
		{
			Registration.insertNewRegistration(tmp);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
		
		//inserimento degli orari inseriti nel calendario dell utente
		for(int i=0;i<schedules.size();i++)
		{
			String[] parts = schedules.get(i).split(" ");
			String day=parts[0];
			String time=parts[1];
			ScheduleBusiness.getInstance();
			try
			{
				//int idSchedule=ScheduleBusiness.getScheduleByDay(day, time, Integer.parseInt(newRegistration.get(3))).getId();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
		return true;
		
	}
}
