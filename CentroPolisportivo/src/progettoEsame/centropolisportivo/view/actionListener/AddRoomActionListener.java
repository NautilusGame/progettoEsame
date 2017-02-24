package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.business.RoomBusiness;
import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.exception.RoomException;
import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.model.CenterManager;
import progettoEsame.centropolisportivo.model.Room;
import progettoEsame.centropolisportivo.view.AddRoom;
import progettoEsame.centropolisportivo.view.Message;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;

public class AddRoomActionListener implements ActionListener {

	private AddRoom addRoomPanel;
	
	public AddRoomActionListener(AddRoom addRoomPanel)
	{
		this.addRoomPanel = addRoomPanel;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		ArrayList<String> newRoom = addRoomPanel.getRoomData();
		if(newRoom.get(0).equals("") || newRoom.get(1).equals("") || newRoom.get(2).equals(""))
		{
			addRoomPanel.removeMessageToPanel();
			addRoomPanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_ROOM_INCOMPLETE_FIELD));
		}
		else
		{
			try {
				Room room = new Room();
				room.setName(newRoom.get(0));
				room.setCenterManager(CenterManager.findByEmail(Session.getInstance().getEmail()));
				room.setDescription(newRoom.get(1));
				room.setCapability(Integer.parseInt(newRoom.get(2)));
				RoomBusiness.getInstance().insert(room);
				addRoomPanel.removeMessageToPanel();
				addRoomPanel.addMessageToPanel(Message.getInstance().printSuccessMsg(ADD_ROOM_SUCCESS_MSG));
			} 
			catch (SessionException | SQLException | RoomException e) 
			{
				addRoomPanel.removeMessageToPanel();
				addRoomPanel.addMessageToPanel(Message.getInstance().printErrorMsg(e.getMessage()));
			}
			catch (NumberFormatException e1)
			{
				addRoomPanel.removeMessageToPanel();
				addRoomPanel.addMessageToPanel(Message.getInstance().printErrorMsg(ADD_ACTIVITY_PRICE_ERROR));
			}
		}

	}

}
