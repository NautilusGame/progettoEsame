package progettoEsame.centropolisportivo.view.actionListener;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import progettoEsame.centropolisportivo.view.LoginRegister;

public class LoginRegisterFocusListener implements FocusListener{

	private LoginRegister lrview;
	public LoginRegisterFocusListener(LoginRegister lrview) {
		this.lrview = lrview;
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource().getClass() == JTextField.class)
		{	
			JTextField obj = (JTextField)e.getSource();
			if(obj.getText().equals(DEFAULT_TEXT_USERNAME_FIELD) && obj.getName().equals(USERNAME_LOGIN_TEXT_FIELD_NAME))
			{
				obj.setText("");
			}
			else if(obj.getText().equals(DEFAULT_TEXT_PASSWORD_FIELD) && obj.getName().equals(PASSWORD_LOGIN_TEXT_FIELD_NAME))
			{
				obj.setText("");
			}
			else if(obj.getText().equals(DEFAULT_TEXT_USERNAME_FIELD) && obj.getName().equals(USERNAME_REGISTER_TEXT_FIELD_NAME))
			{
				obj.setText("");
			}
			else if(obj.getText().equals(DEFAULT_TEXT_PASSWORD_FIELD) && obj.getName().equals(PASSWORD_REGISTER_TEXT_FIELD_NAME))
			{
				obj.setText("");
			}
			else if(obj.getText().equals(DEFAULT_TEXT_EMAIL_FIELD) && obj.getName().equals(EMAIL_REGISTER_TEXT_FIELD_NAME))
			{
				obj.setText("");
			}
			else if(obj.getText().equals(DEFAULT_TEXT_NAME_FIELD) && obj.getName().equals(NAME_REGISTER_TEXT_FIELD_NAME))
			{
				obj.setText("");
			}
			else if(obj.getText().equals(DEFAULT_TEXT_SURNAME_FIELD) && obj.getName().equals(SURNAME_REGISTER_TEXT_FIELD_NAME))
			{
				obj.setText("");
			}
			else if(obj.getText().equals(DEFAULT_TEXT_CONF_PASSWORD_FIELD) && obj.getName().equals(CONF_PASS_REGISTER_TEXT_FIELD_NAME))
			{
				obj.setText("");
			}
			else if(obj.getText().equals(DEFAULT_TEXT_P_N_FIELD) && obj.getName().equals(P_N_REGISTER_TEXT_FIELD_NAME))
			{
				obj.setText("");
			}
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource().getClass() == JTextField.class)
		{	
			JTextField obj = (JTextField)e.getSource();
			if(obj.getText().equals("") && obj.getName().equals(USERNAME_LOGIN_TEXT_FIELD_NAME))
			{
				obj.setText(DEFAULT_TEXT_USERNAME_FIELD);
			}
			else if(obj.getText().equals("") && obj.getName().equals(PASSWORD_LOGIN_TEXT_FIELD_NAME))
			{
				obj.setText(DEFAULT_TEXT_PASSWORD_FIELD);
			}
			else if(obj.getText().equals("") && obj.getName().equals(USERNAME_REGISTER_TEXT_FIELD_NAME))
			{
				obj.setText(DEFAULT_TEXT_USERNAME_FIELD);
			}
			else if(obj.getText().equals("") && obj.getName().equals(PASSWORD_REGISTER_TEXT_FIELD_NAME))
			{
				obj.setText(DEFAULT_TEXT_PASSWORD_FIELD);
			}
			else if(obj.getText().equals("") && obj.getName().equals(EMAIL_REGISTER_TEXT_FIELD_NAME))
			{
				obj.setText(DEFAULT_TEXT_EMAIL_FIELD);
			}
			else if(obj.getText().equals("") && obj.getName().equals(NAME_REGISTER_TEXT_FIELD_NAME))
			{
				obj.setText(DEFAULT_TEXT_NAME_FIELD);
			}
			else if(obj.getText().equals("") && obj.getName().equals(SURNAME_REGISTER_TEXT_FIELD_NAME))
			{
				obj.setText(DEFAULT_TEXT_SURNAME_FIELD);
			}
			else if(obj.getText().equals("") && obj.getName().equals(CONF_PASS_REGISTER_TEXT_FIELD_NAME))
			{
				obj.setText(DEFAULT_TEXT_CONF_PASSWORD_FIELD);
			}
			else if(obj.getText().equals("") && obj.getName().equals(P_N_REGISTER_TEXT_FIELD_NAME))
			{
				obj.setText(DEFAULT_TEXT_P_N_FIELD);
			}
		}

	}

}
