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
			String query = "INSERT INTO room(name, description, capability, center_manager_email) VALUES ('"+newRoom.getName()+"','"+newRoom.getDescription()+"','"+newRoom.getCapability()+"','"+newRoom.getCenterManager().getEmail()+"');";
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
		room.setDescription(row[2]);
		room.setCapability(Integer.parseInt(row[3]));
		room.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[4]));
		return room;

	}
	
	public Room findByName(String name) throws SQLException
	{
		Room room = new Room();
		ArrayList <String[]> result  = DbConnection.getInstance().eseguiQuery("SELECT * FROM room WHERE  name= '"+name+"';");
		if(result.size() == 0) return null;

		String[] row = result.get(0);
		room.setId(Integer.parseInt(row[0]));
		room.setName(row[1]);
		room.setDescription(row[2]);
		room.setCapability(Integer.parseInt(row[3]));
		room.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[4]));
		return room;

	}
	
	public ArrayList<Room> getAllValuebleRoom() throws SQLException
	{
		ArrayList<String[]> result = new ArrayList<>();
		ArrayList<Room> roomList = new ArrayList<>();
		String query = "SELECT * FROM room";
		result = DbConnection.getInstance().eseguiQuery(query);
		if(result.size() == 0) return null;
		
		for(int i = 0;i<result.size();i++)
		{
			Room tmpRoom = new Room();
			String[] row = result.get(i);
			tmpRoom.setId(Integer.parseInt(row[0]));
			tmpRoom.setName(row[1]);
			tmpRoom.setDescription(row[2]);
			tmpRoom.setCapability(Integer.parseInt(row[3]));
			tmpRoom.setCenterManager(CenterManagerDAO.getInstance().findByEmail(row[4]));
			roomList.add(tmpRoom);
		}
		return roomList;
	}

}
