package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	public void insert(TempAlterRegistration newTempAlterRegistration) throws SQLException
	{
			String query = "INSERT INTO temp_alter_registration(registration_id, center_manager_email, member_email, confirmed,level_id) VALUES ("+newTempAlterRegistration.getRegistration().getId()+",'"+newTempAlterRegistration.getCenterManager().getEmail()+"','"+newTempAlterRegistration.getMember().getEmail()+"',"+newTempAlterRegistration.isConfirmed()+","+newTempAlterRegistration.getLevel().getId()+")";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void delete(Integer id) throws SQLException
	{
		String query = "DELETE FROM tempAlterRegistration WHERE id = '"+id+"'";
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
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM tempAlterRegistration WHERE id= "+id+";");
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

}
