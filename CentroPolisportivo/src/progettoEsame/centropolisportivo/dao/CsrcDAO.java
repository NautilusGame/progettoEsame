package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import progettoEsame.centropolisportivo.dbConnection.DbConnection;
import progettoEsame.centropolisportivo.model.Csrc;

public class CsrcDAO  {

	private static CsrcDAO instance;

	public static synchronized CsrcDAO getInstance ()
	{
		if(instance==null)
			instance = new CsrcDAO();
		return instance;
	}

	public void insert(Csrc newCsrc) throws SQLException
	{
			String query = "INSERT INTO csrc(path, deadline, member_email) VALUES ('"+newCsrc.getPath()+"','"+newCsrc.getDeadline()+"','"+newCsrc.getMember().getEmail()+");";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void delete(Integer id) throws SQLException
	{
		String query = "DELETE FROM csrc WHERE id = "+id+";";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void update(Csrc newCsrc) throws SQLException
	{
			String query = "UPDATE csrc SET path='"+newCsrc.getPath()+"',  deadline='"+newCsrc.getDeadline()+"',  member_email='"+newCsrc.getMember().getEmail()+"';";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}
	public Csrc findByEmail(Integer id) throws SQLException
	{
		Csrc csrc = new Csrc();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM csrc WHERE  id= "+id+";");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		csrc.setId(Integer.parseInt(row[0]));
		csrc.setPath(row[1]);
		String date = row[2];
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate = dateFormat.parse(date);
			csrc.setDeadline(new java.sql.Date(myDate.getTime()));
		}
		catch(ParseException exception)
		{
			exception.printStackTrace();
		}
		csrc.setMember(MemberDAO.getInstance().findByEmail(row[3]));
		return csrc;

	}

}
