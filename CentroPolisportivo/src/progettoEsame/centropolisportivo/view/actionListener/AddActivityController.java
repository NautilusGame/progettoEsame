package progettoEsame.centropolisportivo.view.actionListener;

import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.dao.ActivityTypeDAO;
import progettoEsame.centropolisportivo.dao.RoomDAO;
import progettoEsame.centropolisportivo.model.ActivityType;
import progettoEsame.centropolisportivo.model.Room;
import progettoEsame.centropolisportivo.view.AddActivity;

public class AddActivityController {

	private static AddActivityController instance;
	private AddActivity addActivityPanel;
	private ArrayList<Room> roomList;
	private ArrayList<ActivityType> activityTypeList;

	public static synchronized AddActivityController getInstance ()
	{
		if(instance==null)
			instance = new AddActivityController();
		return instance;
	}
	public void addRooms(AddActivity addActivityPanel)
	{
		try {
			this.addActivityPanel = addActivityPanel;
			this.roomList = Room.getAllValuebleRoom();
			if(roomList!=null)
			{
				addActivityPanel.setRoomListModel(this.getRoomsName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void addType(AddActivity addActivityPanel)
	{
		try {
			this.addActivityPanel = addActivityPanel;
			this.activityTypeList = ActivityType.getAllActivityType();
			if(activityTypeList!=null)
			{
				addActivityPanel.setActivityTypeListModel(this.getTypeName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private ArrayList<String> getRoomsName()
	{
		ArrayList<String> tmpArray = new ArrayList<>();
		for(int i = 0;i<this.roomList.size();i++)
		{
			tmpArray.add(roomList.get(i).getName());
		}
		return tmpArray;
	}

	private ArrayList<String> getTypeName()
	{
		ArrayList<String> tmpArray = new ArrayList<>();
		for(int i = 0;i<this.activityTypeList.size();i++)
		{
			tmpArray.add(activityTypeList.get(i).getType());
		}
		return tmpArray;
	}

}
