package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.dbConnection.DbConnection;
import progettoEsame.centropolisportivo.model.CenterManager;

public class CenterManagerDAO {

	private static CenterManagerDAO instance;

	public static synchronized CenterManagerDAO getInstance ()
	{
		if(instance==null)
			instance = new CenterManagerDAO();
		return instance;
	}

	public void insert(CenterManager newCenterManager) throws SQLException
	{
		String query = "INSERT INTO center_manager(email,password,name,surname,phone_number) VALUES ('"+newCenterManager.getEmail()+"','"+newCenterManager.getPassword()+"','"+newCenterManager.getName()+"','"+newCenterManager.getSurname()+"','"+newCenterManager.getPhoneNumber()+"')";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void delete(String email) throws SQLException
	{
		String query = "DELETE FROM center_manager WHERE email = '"+email+"'";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void update(CenterManager newCenterManager)throws SQLException
	{
		String query = "UPDATE center_manager SET name = '"+newCenterManager.getName()+"', SET surname = '"+newCenterManager.getSurname()+"',SET password = '"+newCenterManager.getPassword()+"',SET phone_number = '"+newCenterManager.getPhoneNumber()+"';";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}
	public CenterManager findByEmail(String email) throws SQLException
	{
		CenterManager centerManager = new CenterManager();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM center_manager WHERE email= '"+email+"';");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		centerManager.setEmail(row[0]);
		centerManager.setPassword(row[1]);
		centerManager.setName(row[2]);
		centerManager.setSurname(row[3]);
		centerManager.setPhoneNumber(row[4]);
		return centerManager;

	}

}
