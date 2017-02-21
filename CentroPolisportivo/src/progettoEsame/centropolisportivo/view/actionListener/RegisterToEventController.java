package progettoEsame.centropolisportivo.view.actionListener;

import java.sql.SQLException;

import progettoEsame.centropolisportivo.business.EventBusiness;
import progettoEsame.centropolisportivo.view.Message;
import progettoEsame.centropolisportivo.view.RegisterToEvent;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;
public class RegisterToEventController {

	private RegisterToEvent registerToCompetitionPanel;
	private static RegisterToEventController instance;


	public static synchronized RegisterToEventController getInstance()
	{
		if(instance == null)
		{
			return new RegisterToEventController();
		}
		return instance;
	}

	public void init(int eventId, RegisterToEvent registerToCompetitionPanel)
	{
		this.registerToCompetitionPanel = registerToCompetitionPanel;
		try {
			this.registerToCompetitionPanel.init(EventBusiness.getInstance().findById(eventId));
		} catch (SQLException e) {
			this.registerToCompetitionPanel.addMessageToPanel(Message.getInstance().printErrorMsg(REGISTER_TO_COMPETITION_DB_ERROR));
			e.printStackTrace();
		}
	}

}
