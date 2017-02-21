package progettoEsame.centropolisportivo.model;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.dao.ActivityDAO;

// Generated 26-gen-2017 22.33.09 by Hibernate Tools 5.2.0.CR1

/**
 * Activity generated by hbm2java
 */
public class Activity {

	private int id;
	private ActivityType activityType;
	private CenterManager centerManager;
	private Room room;
	private String name;
	private String description;
	private float cost;
	private String imagePath;

	


	public Activity() {
		super();
	}

	public Activity(int id) {
		this.id = id;
		
	}

	public Activity(int id, ActivityType activityType, CenterManager centerManager, Room room, String name,
			String description, float cost, String imagePath) {
		this.id = id;
		this.activityType = activityType;
		this.centerManager = centerManager;
		this.room = room;
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.imagePath = imagePath;
	}

	

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ActivityType getActivityType() {
		return this.activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	public CenterManager getCenterManager() {
		return this.centerManager;
	}

	public void setCenterManager(CenterManager centerManager) {
		this.centerManager = centerManager;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getCost() {
		return this.cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public static void insert(Activity newActivity)throws SQLException
	{
		ActivityDAO.getInstance().insert(newActivity);
	}
	public static ArrayList<Activity> getAllActivity()throws SQLException
	{
		return ActivityDAO.getInstance().getAllActivity();
	}
	
	public static Activity findByID(int id)throws SQLException
	{
		return ActivityDAO.getInstance().findById(id);
	}

}
