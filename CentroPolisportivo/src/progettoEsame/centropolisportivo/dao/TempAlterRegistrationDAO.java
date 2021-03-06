package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import progettoEsame.centropolisportivo.dbConnection.DbConnection;
import progettoEsame.centropolisportivo.model.TempAlterRegistration;

public class TempAlterRegistrationDAO {

	private static TempAlterRegistrationDAO instance;

	public static synchronized TempAlterRegistrationDAO getInstance ()
	{
		if(instance==null)
			instance = new TempAlterRegistrationDAO();
		return instance;
	}

	public int insert(TempAlterRegistration newTempAlterRegistration) throws SQLException
	{
			String query = "INSERT INTO temp_alter_registration(registration_id, member_email, confirmed,level_id) VALUES ("+newTempAlterRegistration.getRegistration().getId()+",'"+newTempAlterRegistration.getMember().getEmail()+"',"+newTempAlterRegistration.isConfirmed()+","+newTempAlterRegistration.getLevel().getId()+")";
			return DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void delete(Integer id) throws SQLException
	{
		String query = "DELETE FROM temp_alter_registration WHERE id = "+id+"";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void update(TempAlterRegistration newTempAlterRegistration) throws SQLException
	{
			String query = "UPDATE temp_alter_registration SET registration_id='"+newTempAlterRegistration.getRegistration().getId()+"',center_manager_email='"+newTempAlterRegistration.getCenterManager().getEmail()+"',member_email='"+newTempAlterRegistration.getMember().getEmail()+"',confirmed='"+newTempAlterRegistration.isConfirmed()+"', level_id = "+newTempAlterRegistration.getLevel().getId()+";";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}
	
	public TempAlterRegistration findById(int id) throws SQLException
	{
		TempAlterRegistration tempAlterRegistration = new TempAlterRegistration();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM temp_alter_registration WHERE id= "+id+";");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		tempAlterRegistration.setId(Integer.parseInt(row[0]));
		tempAlterRegistration.setRegistration(RegistrationDAO.getInstance().findById(Integer.parseInt(row[1])));
		tempAlterRegistration.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[2]));
		tempAlterRegistration.setMember(MemberDAO.getInstance().findByEmail(row[3]));
		tempAlterRegistration.setConfirmed(Integer.parseInt(row[4]));
		tempAlterRegistration.setLevel(LevelDAO.getInstance().findById(Integer.parseInt(row[5])));
		return tempAlterRegistration;

	}
	public ArrayList<TempAlterRegistration> getAllTempAlertRegistrations()throws SQLException
	{
		String query = "SELECT * FROM temp_alter_registration";
		ArrayList<String[]> result = DbConnection.getInstance().eseguiQuery(query);
		ArrayList<TempAlterRegistration> allTempAlterRegistrations = new ArrayList<TempAlterRegistration>();
		
		if(result.size() == 0)
			return null;
		for(int i = 0;i<result.size();i++)
		{
			TempAlterRegistration tempAlterRegistration = new TempAlterRegistration();
			String[] row = result.get(i);
			tempAlterRegistration.setId(Integer.parseInt(row[0]));
			tempAlterRegistration.setRegistration(RegistrationDAO.getInstance().findById(Integer.parseInt(row[1])));
			tempAlterRegistration.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[2]));
			tempAlterRegistration.setMember(MemberDAO.getInstance().findByEmail(row[3]));
			tempAlterRegistration.setConfirmed(Integer.parseInt(row[4]));
			tempAlterRegistration.setLevel(LevelDAO.getInstance().findById(Integer.parseInt(row[5])));
			allTempAlterRegistrations.add(tempAlterRegistration);
		}
		return allTempAlterRegistrations;
	}

}
