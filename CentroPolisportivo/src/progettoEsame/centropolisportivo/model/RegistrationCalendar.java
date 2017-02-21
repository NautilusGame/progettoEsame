package progettoEsame.centropolisportivo.model;

public class RegistrationCalendar {

	private int id;
	private Schedule schedule;
	private Registration registration;

	public RegistrationCalendar()
	{
		super();
	}

	public RegistrationCalendar(int id, Schedule schedule, Registration registration) {
		super();
		this.id = id;
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
}