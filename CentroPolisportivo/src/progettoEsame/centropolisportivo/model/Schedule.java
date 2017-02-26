package progettoEsame.centropolisportivo.model;

import java.sql.SQLException;
import java.util.ArrayList;
import progettoEsame.centropolisportivo.dao.ScheduleDAO;

// Generated 26-gen-2017 22.33.09 by Hibernate Tools 5.2.0.CR1



public class Schedule  {

	private Integer id;
	private Activity activity;
	private Trainer trainer;
	private String day;
	private String time;


	
	public Schedule() {
		super();
	}

	public Schedule(int id) {
		this.id=id;
	}
	
	public Schedule(Activity activity, Trainer trainer, String day, String time) {
		this.activity = activity;
		this.trainer = trainer;
		this.day = day;
		this.time = time;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Trainer getTrainer() {
		return this.trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public String getDay() {
		return this.day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public static void insert(Schedule newSchedule)throws SQLException
	{
		ScheduleDAO.getInstance().insert(newSchedule);
	}

	public static Schedule findById(int id)throws SQLException
	{
		return ScheduleDAO.getInstance().findById(id);
	}
	
	public static ArrayList<Schedule> getScheduleByActivity(int idActivity) throws SQLException
	{
		return ScheduleDAO.getInstance().getAllScheduleByActivity(idActivity);
	}
	
	
	public static Schedule getScheduleByDay(String day,String time,int idActivity) throws SQLException
	{
		return ScheduleDAO.getInstance().getScheduleByDay(day, time, idActivity);
	}
	
}
