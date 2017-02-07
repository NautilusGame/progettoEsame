package progettoEsame.centropolisportivo.view.actionListener;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;
import static progettoEsame.centropolisportivo.business.ConstantClass.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import progettoEsame.centropolisportivo.business.ConstantClass;
import progettoEsame.centropolisportivo.business.LoginBusiness;
import progettoEsame.centropolisportivo.business.RegistrationBusiness;
import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.exception.LoginException;
import progettoEsame.centropolisportivo.exception.RegistrationException;
import progettoEsame.centropolisportivo.model.CenterManager;
import progettoEsame.centropolisportivo.model.Member;
import progettoEsame.centropolisportivo.model.Trainer;
import progettoEsame.centropolisportivo.view.LoginRegister;
import progettoEsame.centropolisportivo.view.MainFrame;
import progettoEsame.centropolisportivo.view.Message;
import progettoEsame.centropolisportivo.view.Template;
public class LoginRegisterActionListener implements ActionListener
{
	private LoginRegister lrview;
	private MainFrame mf;
	public LoginRegisterActionListener(LoginRegister lrview, MainFrame mf)
	{
		this.lrview = lrview;
		this.mf = mf;
	}
	public void actionPerformed(ActionEvent e){

		if(e.getActionCommand().equals(TRAINER_RADIOBUTTON_ACTION_CMD))
		{
			this.lrview.makeVisiblePhoneNumberField();
		}
		else if(e.getActionCommand().equals(MEMBER_RADIOBUTTON_ACTION_CMD))
		{
			this.lrview.makeInvisiblePhoneNumberField();
		}
		else if(e.getActionCommand().equals(LOGIN_BUTTON_ACTION_CMD))
		{

			ArrayList<String> loginData = lrview.getLoginData();
			if(loginData.get(0).equals(DEFAULT_TEXT_EMAIL_FIELD) ||  loginData.get(1).equals(DEFAULT_TEXT_PASSWORD_FIELD))
			{
				lrview.addMessageToPanel(Message.getInstance().printErrorMsg("All field must be completed"));
			}
			else
			{
				try {
					boolean resMember = LoginBusiness.getInstance().LoginMember(loginData.get(0), loginData.get(1));
					boolean resTrainer = LoginBusiness.getInstance().LoginTrainer(loginData.get(0), loginData.get(1));
					boolean resCenterManager = LoginBusiness.getInstance().LoginCenterManager(loginData.get(0), loginData.get(1));
					if(!resCenterManager && !resTrainer && !resMember)
					{
						lrview.addMessageToPanel(Message.getInstance().printErrorMsg(ConstantClass.USERNAME_NOTFOUND));
					}
					else
					{
						lrview.addMessageToPanel(Message.getInstance().printSuccessMsg(SUCCES_LOGIN_MSG));
						mf.remove(lrview);
						mf.add(new Template(this.mf));
						mf.repaint();
					}

				} catch (LoginException | SQLException e1) {
					lrview.addMessageToPanel(Message.getInstance().printErrorMsg(e1.getMessage()));
				}
			}

		}

		else if(e.getActionCommand().equals(REGISTER_BUTTON_ACTION_CMD))
		{
			ArrayList<String> registerData = lrview.getRegisterData();
			if(registerData.get(0).equals(DEFAULT_TEXT_SURNAME_FIELD) ||  registerData.get(1).equals(DEFAULT_TEXT_NAME_FIELD) ||  registerData.get(2).equals("")  ||  registerData.get(3).equals("")  ||  registerData.get(5).equals(DEFAULT_TEXT_EMAIL_FIELD))
			{
				lrview.removeMessageToPanel();
				lrview.addMessageToPanel(Message.getInstance().printErrorMsg(REGISTER_DATA_INCOMPLETED_MSG));
			}
			else if(!(registerData.get(3).equals(registerData.get(4))))
			{
				lrview.removeMessageToPanel();
				lrview.addMessageToPanel(Message.getInstance().printErrorMsg(REGISTER_PASSWORD_NOT_EQUALS_MSG));
			}
			else
			{
				if(registerData.get(8).equals(TRAINER_RADIOBUTTON_ACTION_CMD))
				{
					Trainer newTrainer = new Trainer(registerData.get(6), new CenterManager(), registerData.get(3),registerData.get(1), registerData.get(0),registerData.get(5), 0);
					try {
						if(RegistrationBusiness.getInstance().checkTrainerDataRegistration(newTrainer))
						{
							lrview.removeMessageToPanel();
							lrview.addMessageToPanel(Message.getInstance().printSuccessMsg(SUCCES_REGISTRATION_MSG));
							mf.remove(lrview);
							mf.add(new Template(this.mf));
							mf.repaint();
						}
					} catch (RegistrationException | SQLException e1) {

						lrview.addMessageToPanel(Message.getInstance().printErrorMsg(e1.getMessage()));
					}
				}
				else if(registerData.get(8).equals(MEMBER_RADIOBUTTON_ACTION_CMD))
				{
					CenterManager cm = new CenterManager();
					Member newMember = new Member(registerData.get(6), cm, registerData.get(1), registerData.get(0),Date.valueOf(registerData.get(7)), registerData.get(3), 0);
					try {
						if(RegistrationBusiness.getInstance().checkMemberDataRegistration(newMember))
						{
							lrview.removeMessageToPanel();
							lrview.addMessageToPanel(Message.getInstance().printSuccessMsg(SUCCES_REGISTRATION_MSG));
						}
					} catch (RegistrationException | SQLException e1) {
						lrview.addMessageToPanel(Message.getInstance().printErrorMsg(e1.getMessage()));
					}
				}
			}

		}

	}

}
