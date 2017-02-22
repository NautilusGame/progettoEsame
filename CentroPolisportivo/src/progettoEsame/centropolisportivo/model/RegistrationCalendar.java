package progettoEsame.centropolisportivo.model;

import java.sql.SQLException;

import progettoEsame.centropolisportivo.dao.ActivityDAO;
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
	
	public RegistrationCalendar(Schedule schedule, Registration registration) {
		this.schedule = schedule;
		this.registration = registration;
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
	
	
}