package progettoEsame.centropolisportivo.model;
// Generated 26-gen-2017 22.33.09 by Hibernate Tools 5.2.0.CR1



/**
 * Schedule generated by hbm2java
 */
public class Schedule  {

	private Integer id;
	private Activity activity;
	private Trainer trainer;
	private String day;
	private int time;


	
	public Schedule() {
		super();
	}

	public Schedule(Activity activity, Trainer trainer, String day, int time) {
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

	public int getTime() {
		return this.time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
