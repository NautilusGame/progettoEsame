package progettoEsame.centropolisportivo.business;

import java.sql.SQLException;

import progettoEsame.centropolisportivo.exception.RoomException;
import progettoEsame.centropolisportivo.model.Room;
import static progettoEsame.centropolisportivo.business.ConstantClass.*;
public class RoomBusiness {
	
	private static RoomBusiness instance;

	public static synchronized RoomBusiness getInstance()
	{
		if(instance == null)
			return new RoomBusiness();
		return instance;
	}
	
	public void insert(Room newRoom) throws SQLException, RoomException
	{
		if(Room.findByName(newRoom.getName()) ==null)
		{
			Room.insert(newRoom);
		}
		else 
		{
			throw new RoomException(ROOM_IS_PRESENT);
		}
	}

}
