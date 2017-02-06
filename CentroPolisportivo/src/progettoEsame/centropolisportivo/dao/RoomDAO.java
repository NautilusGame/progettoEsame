package progettoEsame.centropolisportivo.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import progettoEsame.centropolisportivo.dbConnection.DbConnection;
import progettoEsame.centropolisportivo.model.Room;

public class RoomDAO  {

	private static RoomDAO instance;

	public static synchronized RoomDAO getInstance ()
	{
		if(instance==null)
			instance = new RoomDAO();
		return instance;
	}

	public void insert(Room newRoom) throws SQLException
	{
			String query = "INSERT INTO room(name, description, capability, center_manager_email) VALUES ('"+newRoom.getName()+"','"+newRoom.getDescription()+"','"+newRoom.getCapability()+"','"+newRoom.getCenterManager().getEmail()+");";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void delete(Integer id) throws SQLException
	{
		String query = "DELETE FROM room WHERE id = "+id+";";
		DbConnection.getInstance().eseguiAggiornamento(query);
	}

	public void update(Room newRoom) throws SQLException
	{
			String query = "UPDATE member  SET name = '"+newRoom.getName()+"', description = '"+newRoom.getDescription()+"',' capability = '"+newRoom.getCapability()+"', center_manager_email = '"+newRoom.getCenterManager().getEmail()+";";
			DbConnection.getInstance().eseguiAggiornamento(query);
	}
	public Room findById(Integer id) throws SQLException
	{
		Room room = new Room();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM room WHERE  id= "+id+";");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		room.setId(Integer.parseInt(row[0]));
		room.setName(row[1]);
		room.setDescription(row[3]);
		room.setCapability(Integer.parseInt(row[4]));
		room.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[5]));
		return room;

	}

}
