package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import progettoEsame.centropolisportivo.dbConnection.DbConnection;
import progettoEsame.centropolisportivo.model.Level;

public class LevelDAO  {

	private static LevelDAO instance;

	public static synchronized LevelDAO getInstance ()
	{
		if(instance==null)
			instance = new LevelDAO();
		return instance;
	}

	public void insert(Level newLevel) throws SQLException
	{
			String query = "INSERT INTO level( name, description, center_manager_email) VALUES ('"+newLevel.getName()+"','"+newLevel.getDescription()+"','"+newLevel.getCenterManager().getEmail()+");";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void delete(Integer id) throws SQLException
	{
		String query = "DELETE FROM level WHERE id = "+id+";";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void update(Level newLevel) throws SQLException
	{
		String query = "UPDATE level SET name='"+newLevel.getName()+"',description='"+newLevel.getDescription()+"',center_manager_email='"+newLevel.getCenterManager().getEmail()+"'";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}
	public Level findById(Integer id) throws SQLException
	{
		Level level = new Level();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM level WHERE  id= "+id+";");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		level.setId(Integer.parseInt(row[0]));
		level.setName(row[1]);
		level.setDescription(row[2]);
		level.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[3]));
		return level;

	}

}

