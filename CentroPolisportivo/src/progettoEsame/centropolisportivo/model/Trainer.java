package progettoEsame.centropolisportivo.model;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.dao.TrainerDAO;

public class Trainer {

	private String email;
	private CenterManager centerManager;
	private String password;
	private String name;
	private String surname;
	private String phoneNumber;
	private int confirmed;

	public Trainer()
	{
		
	}

	public Trainer(String email, CenterManager centerManager, String password, String name, String surname,
			String phoneNumber, int confirmed) {
		this.email = email;
		this.centerManager = centerManager;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.confirmed = confirmed;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CenterManager getCenterManager() {
		return this.centerManager;
	}

	public void setCenterManager(CenterManager centerManager) {
		this.centerManager = centerManager;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int isConfirmed() {
		return this.confirmed;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}
	
	public static void insert(Trainer newTrainer) throws SQLException
	{
		TrainerDAO.getInstance().insert(newTrainer);
	}
	
	public static Trainer findByEmail(String email)throws SQLException
	{
		return TrainerDAO.getInstance().findByEmail(email);
	}
	public static void update(Trainer trainer) throws SQLException
	{
		TrainerDAO.getInstance().update(trainer);
	}
	
	public static void delete(String email) throws SQLException
	{
		TrainerDAO.getInstance().delete(email);
	}
	
	public static ArrayList<Trainer> getAllUnconfirmedTrainer()throws SQLException
	{
		return TrainerDAO.getInstance().getAllUnconfirmedTrainer();
	}

}
