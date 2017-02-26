package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
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
			String query = "INSERT INTO csrc(path, member_email,event_id) VALUES ('"+newCsrc.getPath()+"','"+newCsrc.getMember().getEmail()+"',"+newCsrc.getEvent().getId()+");";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void delete(Integer id) throws SQLException
	{
		String query = "DELETE FROM csrc WHERE id = "+id+";";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void update(Csrc newCsrc) throws SQLException
	{
			String query = "UPDATE csrc SET path='"+newCsrc.getPath()+"', member_email='"+newCsrc.getMember().getEmail()+"',event_id = "+newCsrc.getEvent().getId()+";";
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
		csrc.setMember(MemberDAO.getInstance().findByEmail(row[2]));
		csrc.setEvent(EventDAO.getInstance().findById(Integer.parseInt(row[3])));
		return csrc;

	}

}
