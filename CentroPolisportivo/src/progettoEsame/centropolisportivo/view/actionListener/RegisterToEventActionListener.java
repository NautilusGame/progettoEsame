package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import progettoEsame.centropolisportivo.business.CsrcBusiness;
import progettoEsame.centropolisportivo.business.EventBusiness;
import progettoEsame.centropolisportivo.business.MemberBusiness;
import progettoEsame.centropolisportivo.business.RegistrationBusiness;
import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.model.Csrc;
import progettoEsame.centropolisportivo.model.Registration;
import progettoEsame.centropolisportivo.view.Message;
import progettoEsame.centropolisportivo.view.RegisterToEvent;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;
public class RegisterToEventActionListener implements ActionListener {

	private RegisterToEvent registerToCompetitionPanel;
	public RegisterToEventActionListener(RegisterToEvent registerToCompetitionPanel) {
		this.registerToCompetitionPanel = registerToCompetitionPanel;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals(REGISTER_TO_COMPETITION_ENROL_BUTTON_ACTION_CMD))
		{
			Registration newRegistration = this.registerToCompetitionPanel.getRegistration();
			Csrc newCsrc = this.registerToCompetitionPanel.getCSRC();

			if(newCsrc.getPath().equals(""))
			{
				this.registerToCompetitionPanel.removeMessageToPanel();
				this.registerToCompetitionPanel.addMessageToPanel(Message.getInstance().printErrorMsg(REGISTER_TO_COMPETITION_PATH_BLANK));
			}
			else
			{
				try {
					newRegistration.setMember(MemberBusiness.getInstance().findByEmail(Session.getInstance().getEmail()));
					System.out.println(newRegistration.getMember().getEmail());
					newRegistration.setCost(EventBusiness.getInstance().findById(newRegistration.getEvent().getId()).getCost());
					newCsrc.setMember(MemberBusiness.getInstance().findByEmail(Session.getInstance().getEmail()));
				}catch (SQLException | SessionException e) {	

					this.registerToCompetitionPanel.removeMessageToPanel();
					this.registerToCompetitionPanel.addMessageToPanel(Message.getInstance().printErrorMsg(REGISTER_TO_COMPETITION_DB_ERROR));
				}
				try {
					if(!EventBusiness.getInstance().findById(newRegistration.getEvent().getId()).isFree())
					{
						Session.getInstance().saveOnSession("newRegistration", newRegistration);
						Session.getInstance().saveOnSession("newCsrc", newCsrc);
						this.registerToCompetitionPanel.makeInvisibleMainPanel(newRegistration.getCost(),newRegistration.getEvent().getId());
					}
					else
					{
						newRegistration.setCost(0);
						RegistrationBusiness.getInstance().insert(newRegistration);
						CsrcBusiness.getInstance().insert(newCsrc);		
						this.registerToCompetitionPanel.addMessageToPanel(Message.getInstance().printSuccessMsg(REGISTER_TO_COMPETITION_SUCCESS));


					}
				} catch (SQLException e) {
					this.registerToCompetitionPanel.removeMessageToPanel();
					this.registerToCompetitionPanel.addMessageToPanel(Message.getInstance().printErrorMsg(REGISTER_TO_COMPETITION_DB_ERROR));
					e.printStackTrace();
				}
			}

		} 

	}

}
