package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import progettoEsame.centropolisportivo.dbConnection.DbConnection;
import progettoEsame.centropolisportivo.model.Member;
import progettoEsame.centropolisportivo.view.AcceptRegistrationProposal;

public class MemberDAO {

	private static MemberDAO instance;

	public static synchronized MemberDAO getInstance ()
	{
		
		if(instance==null)
			instance = new MemberDAO();
		return instance;
	}

	public void insert(Member newMember) throws SQLException
	{
		String query = "INSERT INTO member(name,surname,birthday,email,password,confirmed) VALUES ('"+newMember.getName()+"','"+newMember.getSurname()+"','"+newMember.getBirthday()+"','"+newMember.getEmail()+"','"+newMember.getPassword()+"',"+newMember.getConfirmed()+")";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void delete(String email) throws SQLException
	{
		String query = "DELETE FROM member WHERE email = '"+email+"'";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void update(Member newMember) throws SQLException
	{
		String query = "UPDATE member SET name = '"+newMember.getName()+"', surname = '"+newMember.getSurname()+"', password = '"+newMember.getPassword()+"', birthday = '"+newMember.getBirthday()+"', center_manager_email = '"+newMember.getCenterManager().getEmail()+"', confirmed = "+newMember.getConfirmed()+" WHERE email = '"+newMember.getEmail()+"';";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}
	public Member findByEmail(String email) throws SQLException
	{
		Member member = new Member();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM member WHERE email= '"+email+"';");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		member.setName(row[0]);
		member.setSurname(row[1]);
		String date = row[2];
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate = dateFormat.parse(date);
			member.setBirthday(new java.sql.Date(myDate.getTime()));
		}
		catch(ParseException exception)
		{
			exception.printStackTrace();
		}
		member.setEmail(row[3]);
		member.setPassword(row[4]);
		member.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[5]));
		member.setConfirmed(Integer.parseInt(row[6]));
		return member;

	}

	public ArrayList<Member> getAllUnconfirmedMember()throws SQLException
	{
		String query = "SELECT * FROM member WHERE confirmed = 0";
		ArrayList<String[]> result = DbConnection.getInstance().eseguiQuery(query);
		ArrayList<Member> unconfirmedMember = new ArrayList<Member>();
		if(result.size() == 0)
			return null;
		for(int i = 0;i<result.size();i++)
		{

			Member tmpMember = new Member();
			String[] row = result.get(i);
			tmpMember.setName(row[0]);
			tmpMember.setSurname(row[1]);
			String date = row[2];
			try
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date myDate = dateFormat.parse(date);
				tmpMember.setBirthday(new java.sql.Date(myDate.getTime()));
			}
			catch(ParseException exception)
			{
				exception.printStackTrace();
			}
			tmpMember.setEmail(row[3]);
			tmpMember.setPassword(row[4]);
			tmpMember.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[5]));
			tmpMember.setConfirmed(Integer.parseInt(row[6]));
			unconfirmedMember.add(tmpMember);
		}
		return unconfirmedMember;
	}

}
