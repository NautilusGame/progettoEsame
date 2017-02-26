package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.model.Activity;
import progettoEsame.centropolisportivo.model.Level;
import progettoEsame.centropolisportivo.model.Member;
import progettoEsame.centropolisportivo.model.Registration;
import progettoEsame.centropolisportivo.model.RegistrationCalendar;
import progettoEsame.centropolisportivo.model.Schedule;
import progettoEsame.centropolisportivo.model.TempAlterRegistration;

public class RegistrationActivityBusiness 
{
	private static RegistrationActivityBusiness instance;
	private int idRegistration;
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
		int idRegistration;
		
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
		}
		
		try
		{
			idRegistration=Registration.insertNewRegistration(tmp);
			this.setIdRegistration(idRegistration);
		}
		catch (Exception e) 
		{
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
				int idSchedule=ScheduleBusiness.getScheduleByDay(day, time, Integer.parseInt(newRegistration.get(3))).getId();
				TempAlterRegistration t = new TempAlterRegistration();
				t.setId(null);
				RegistrationCalendar calendar = new RegistrationCalendar(new Schedule(idSchedule),new Registration(idRegistration),t);
				RegistrationCalendar.insert(calendar);
			}
			catch(Exception e)
			{
				return false;
			}
		}
		return true;
	}
	
	public void insertNewRegistration(Registration newRegistration)
	{
		try {
			this.setIdRegistration(Registration.insertNewRegistration(newRegistration));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
	}
	public void setIdRegistration(int id)
	{
		this.idRegistration = id;
	}
	
	public int getIdRegistration()
	{
		return this.idRegistration;
	}
}
