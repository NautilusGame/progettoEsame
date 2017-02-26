package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.model.Registration;
import progettoEsame.centropolisportivo.model.RegistrationCalendar;
import progettoEsame.centropolisportivo.model.TempAlterRegistration;


public class ConfirmAlterRegistrationBusiness {

	private static ConfirmAlterRegistrationBusiness instance;
	private static ArrayList<TempAlterRegistration> allRequests;
	
	public static synchronized ConfirmAlterRegistrationBusiness getInstance()
	{
		if(instance == null)
			return new ConfirmAlterRegistrationBusiness();
		return instance;
	}
	
	public ArrayList<TempAlterRegistration> getAllRequests()throws SQLException
	{
		allRequests = TempAlterRegistration.getAllRequests();
		return allRequests;		
	}
	
	public void deleteTempAlterRegistration(int id) throws SQLException
	{
		TempAlterRegistration.deleteAlterRegistration(id);
	}
	
	public void deleteAlterCalendar(int id) throws SQLException
	{
		ArrayList<RegistrationCalendar> newAlterDays= RegistrationCalendar.findCalendarByTempAlterRegistrations(id);
		int day;
		
		if(newAlterDays!=null)
			day=newAlterDays.size();
		else 
			day=0;
		
		if(day>0)
		{
			for(int i=0;i<day;i++)
			{
				RegistrationCalendar.deleteByTempAlteRegistration(id);
			}
		}		
	}
	
	public void updateRegistration(int idRegistration, int idLevel) throws SQLException
	{
		Registration.updateLevelRegistration(idRegistration, idLevel);
		ArrayList<RegistrationCalendar> newAlterDays= RegistrationCalendar.findCalendarByRegistrations(idRegistration);
		int day;		
		
		if(newAlterDays!=null)
			day=newAlterDays.size();
		else 
			day=0;
		
		if(day>0)
		{
			for(int i=0;i<day;i++)
			{
				RegistrationCalendar.deleteByRegistration(idRegistration);
				RegistrationCalendar.updateForeignKeyTempAlter(idRegistration);
			}
		}		
	}
}
