package progettoEsame.centropolisportivo.model;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.dao.MemberDAO;

/**
 * Member generated by hbm2java
 */
public class Member {

	private String email;
	private CenterManager centerManager;
	private String name;
	private String surname;
	private java.sql.Date birthday;
	private String password;
	private int confirmed;
	public Member()
	{
		
	}

	public Member(String email, CenterManager centerManager, String name, String surname, java.sql.Date birthday,
			String password, int confirmed) {
		this.email = email;
		this.centerManager = centerManager;
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.password = password;
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

	public java.sql.Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(java.sql.Date birthday) {
		this.birthday = birthday;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int isConfirmed() {
		return this.confirmed;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}
	
	public static void insert(Member newMember) throws SQLException
	{
		MemberDAO.getInstance().insert(newMember);
	}
	
	public static Member findByEmail(String email)throws SQLException
	{
		return MemberDAO.getInstance().findByEmail(email);
	}
	public static void update(Member member) throws SQLException
	{
		MemberDAO.getInstance().update(member);
	}
	
	public static void delete(String email) throws SQLException
	{
		MemberDAO.getInstance().delete(email);
	}
	public static ArrayList<Member> getAllUnconfirmedMember()throws SQLException
	{
		return MemberDAO.getInstance().getAllUnconfirmedMember();
	}
}
