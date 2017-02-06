package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import progettoEsame.centropolisportivo.dbConnection.DbConnection;
import progettoEsame.centropolisportivo.model.Trainer;

public class TrainerDAO {
	
	private static TrainerDAO instance;

	public static synchronized TrainerDAO getInstance ()
	{
		if(instance==null)
			instance = new TrainerDAO();
		return instance;
	}

	public void insert(Trainer newTrainer) throws SQLException
	{
		String query = "INSERT INTO trainer(email,password,name,surname,phone_number,confirmed) VALUES ('"+newTrainer.getEmail()+"','"+newTrainer.getPassword()+"','"+newTrainer.getName()+"','"+newTrainer.getSurname()+"','"+newTrainer.getPhoneNumber()+"',"+newTrainer.isConfirmed()+")";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void delete(String email) throws SQLException
	{
		String query = "DELETE FROM trainer WHERE email = '"+email+"'";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void update(Trainer newTrainer)throws SQLException
	{
		String query = "UPDATE trainer SET name = '"+newTrainer.getName()+"', surname = '"+newTrainer.getSurname()+"', password = '"+newTrainer.getPassword()+"', phone_number = '"+newTrainer.getPhoneNumber()+"', center_manager_email = '"+newTrainer.getCenterManager().getEmail()+"', confirmed = "+newTrainer.isConfirmed()+" WHERE email = '"+newTrainer.getEmail()+"';";
		System.out.println(query);
		DbConnection.getInstance().eseguiAggiornamento(query);
	}
	public Trainer findByEmail(String email) throws SQLException
	{
		Trainer trainer = new Trainer();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM trainer WHERE email= '"+email+"';");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		trainer.setEmail(row[0]);
		trainer.setPassword(row[1]);
		trainer.setName(row[2]);
		trainer.setSurname(row[3]);
		trainer.setPhoneNumber(row[4]);
		trainer.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[5]));
		trainer.setConfirmed(Integer.parseInt(row[6]));
		return trainer;

	}
	
	public ArrayList<Trainer> getAllUnconfirmedTrainer()throws SQLException
	{
		String query = "SELECT * FROM trainer WHERE confirmed = 0";
		ArrayList<String[]> result = DbConnection.getInstance().eseguiQuery(query);
		ArrayList<Trainer> unconfirmedTrainer = new ArrayList<Trainer>();
		if(result.size() == 0)
			return null;
		for(int i = 0;i<result.size();i++)
		{

			Trainer tmpTrainer = new Trainer();
			String[] row = result.get(i);
			tmpTrainer.setEmail(row[0]);
			tmpTrainer.setPassword(row[1]);
			tmpTrainer.setName(row[2]);
			tmpTrainer.setSurname(row[3]);
			tmpTrainer.setPhoneNumber(row[4]);
			tmpTrainer.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[5]));
			tmpTrainer.setConfirmed(Integer.parseInt(row[6]));
			unconfirmedTrainer.add(tmpTrainer);
		}
		return unconfirmedTrainer;
	}

}
