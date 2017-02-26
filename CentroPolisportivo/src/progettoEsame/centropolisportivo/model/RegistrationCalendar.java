package progettoEsame.centropolisportivo.model;

import java.sql.SQLException;
import java.util.ArrayList;
import progettoEsame.centropolisportivo.dao.RegistrationCalendarDAO;

public class RegistrationCalendar {

	private int id;
	private Schedule schedule;
	private Registration registration;
	private TempAlterRegistration tempAlterRegistration;
	
	public RegistrationCalendar()
	{
		super();
	}

	public RegistrationCalendar(int id, Schedule schedule, Registration registration, TempAlterRegistration tempAlterRegistration) {
		super();
		this.id = id;
		this.schedule = schedule;
		this.registration = registration;
	}
	
	public RegistrationCalendar(Schedule schedule, Registration registration,TempAlterRegistration tempAlterRegistration) {
		this.schedule = schedule;
		this.registration = registration;
		this.tempAlterRegistration=tempAlterRegistration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}
	
	public static void insert(RegistrationCalendar newCalendar)throws SQLException
	{
		RegistrationCalendarDAO.getInstance().insert(newCalendar);
	}

	public TempAlterRegistration getTempAlterRegistration() {
		return tempAlterRegistration;
	}

	public void setTempAlterRegistration(TempAlterRegistration tempAlterRegistration) {
		this.tempAlterRegistration = tempAlterRegistration;
	}
	
	public static void deleteByTempAlteRegistration(Integer id) throws SQLException
	{
		RegistrationCalendarDAO.getInstance().deleteByTempAlteRegistration(id);
	}
	
	public static ArrayList<RegistrationCalendar> findCalendarByTempAlterRegistrations(int id) throws SQLException
	{
		return RegistrationCalendarDAO.getInstance().findCalendarByTempAlterRegistrations(id);
	}
	
	public static void deleteByRegistration(Integer id) throws SQLException
	{
		RegistrationCalendarDAO.getInstance().deleteByRegistration(id);
	}
	
	public static ArrayList<RegistrationCalendar> findCalendarByRegistrations(int id) throws SQLException
	{
		return RegistrationCalendarDAO.getInstance().findCalendarByRegistrations(id);
	}
	
	public static void updateForeignKeyTempAlter(int id) throws SQLException
	{
		RegistrationCalendarDAO.getInstance().updateForeignKeyTempAlter(id);
	}
	
	public static ArrayList<RegistrationCalendar> findByRegistrationId(int id) throws SQLException
	{
		return RegistrationCalendarDAO.getInstance().findByRegistrationId(id);
	}
	
}