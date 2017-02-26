package progettoEsame.centropolisportivo.model;
// Generated 26-gen-2017 22.33.09 by Hibernate Tools 5.2.0.CR1

import java.sql.SQLException;
import progettoEsame.centropolisportivo.dao.CenterManagerDAO;


/**
 * CenterManager generated by hbm2java
 */
public class CenterManager  {

	private String email;
	private String password;
	private String name;
	private String surname;
	private String phoneNumber;

	public CenterManager() {
		this.email=null;
	}


	public CenterManager(String email, String password, String name, String surname, String phoneNumber) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public static void addNewMember(CenterManager newCenterManager) throws SQLException
	{
		CenterManagerDAO.getInstance().insert(newCenterManager);
	}
	
	public static CenterManager findByEmail(String email)throws SQLException
	{
		return CenterManagerDAO.getInstance().findByEmail(email);
	}
	
	public static void update(CenterManager newCenterManager) throws SQLException
	{
		CenterManagerDAO.getInstance().update(newCenterManager);
	}
	
}
