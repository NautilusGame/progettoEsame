package progettoEsame.centropolisportivo.view;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.toedter.calendar.JCalendar;

import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.view.actionListener.ControllerSettings;
import progettoEsame.centropolisportivo.view.actionListener.SessionCheck;
import progettoEsame.centropolisportivo.view.actionListener.SettingsActionListener;

public class Settings extends JPanel 
{

	private MainFrame mf;

	private JTextField nameRegister;//nome utente per il register
	private JTextField surnameRegister;//cognome utente per il register
	private JPasswordField currentPassword;//conferma della password per il register
	private JPasswordField newPassword; 
	private JPasswordField confirmNewPassword;//conferma della password per il register
	private JTextField phoneNumberRegister;//numero di telefono per il trainer(a scomparsa)

	
	private JButton save; //bottone di registrazione
	private JButton changePasswordButton;
	private JButton undoChangePassword;
	
	private JLabel nameLabel; //label nome utente nel pannello di registrazione
	private JLabel surnameLabel; //label cognome nel pannello di registrazione
	private JLabel birthdayLabel; //...
	private JLabel currentPasswordLabel; 
	private JLabel confirmPasswordLabel; //label di conferma della password nel pannello di registrazione
	private JLabel newPasswordLabel;
	private JLabel phoneNumberLabel; 
	private JLabel title; 
	private JLabel msg;
	
	
	private GridBagConstraints gbc; //vincolo per il gridBagLayout
	private JCalendar dataPicker;
	
	
	public Settings (MainFrame mf)
	{
		this.mf=mf;
		
		this.setLayout(new GridBagLayout());//set del layout al pannello "this"
		this.gbc = new GridBagConstraints();//inizializzazione del vincolo
		
		//inizializzazione degli elementi
		this.title=new JLabel(TITLE_SETTINGS);
		this.nameLabel = new JLabel(NAME_LABEL_TEXT);
		this.surnameLabel = new JLabel(SURNAME_LABEL_TEXT);
		this.birthdayLabel = new JLabel(BIRTHDAY_LABEL_TEXT);
		this.phoneNumberLabel = new JLabel(P_N_LABEL_TEXT);		
		this.newPasswordLabel = new JLabel(NEW_PASSWORD_LABEL_TEXT);
		this.confirmPasswordLabel = new JLabel(CONFIRM_PASSWORD_LABEL_TEXT);
		this.currentPasswordLabel = new JLabel(CURRENT_PASSWORD_LABEL_TEXT); //inizializzazione label
		this.msg = new JLabel();
		
		this.nameRegister = new JTextField(TEXT_FIELD_DIMENSION);
		this.surnameRegister = new JTextField(TEXT_FIELD_DIMENSION);
		this.phoneNumberRegister = new JTextField(TEXT_FIELD_DIMENSION);
		
		this.currentPassword = new JPasswordField(TEXT_FIELD_DIMENSION);
		this.newPassword = new JPasswordField(TEXT_FIELD_DIMENSION);
		this.confirmNewPassword = new JPasswordField(TEXT_FIELD_DIMENSION);		
		
		this.dataPicker = new JCalendar();
		this.save = new JButton(BUTTON_SAVE_SETTINGS);
		this.changePasswordButton = new JButton(BUTTON_CHANGE_PASSWORD);
		this.undoChangePassword = new JButton(UNDO_PASSWORD_BUTTON);
		
		//sett dei cambi comuni delle password che devono essere nascosti
		this.hiddenChangePassword();
		
		this.dataPicker.setVisible(false);		
		if (this.getTypeUser().equals("member"))
		{
			this.dataPicker.setVisible(true);
			this.birthdayLabel.setVisible(true);
			this.phoneNumberLabel.setVisible(false);
			this.phoneNumberRegister.setVisible(false);
			
			try
			{
				this.setField(ControllerSettings.getInstance().getValueFromMember());
			}
			catch (SQLException sql)
			{
				//TODO aggiungere messaggio di evenuale errore
			}
			catch (SessionException session)
			{
				//TODO aggiungere messaggio di evenuale errore
			}
		}
		else if (this.getTypeUser().equals("trainer"))
		{
			this.dataPicker.setVisible(false);
			this.birthdayLabel.setVisible(false);
			
			try
			{
				this.setField(ControllerSettings.getInstance().getValueFromTrainer());
			}
			catch (SQLException sql)
			{
				//TODO aggiungere messaggio di evenuale errore
			}
			catch (SessionException session)
			{
				//TODO aggiungere messaggio di evenuale errore
			}
		}
		else
		{
			this.dataPicker.setVisible(false);
			this.birthdayLabel.setVisible(false);
			
			try
			{
				this.setField(ControllerSettings.getInstance().getValueFromCenterManager());
			}
			catch (SQLException sql)
			{
				//TODO aggiungere messaggio di evenuale errore
			}
			catch (SessionException session)
			{
				//TODO aggiungere messaggio di evenuale errore
			}
		}
		
		//settaggio grafico 
		this.newPassword.setForeground(Color.GRAY);
		this.surnameRegister.setForeground(Color.GRAY);
		this.nameRegister.setForeground(Color.GRAY);
		this.currentPassword.setForeground(Color.GRAY);
		this.phoneNumberRegister.setForeground(Color.GRAY);//setting del colore delle JTextField
		
		//set dei nomi
		this.newPassword.setName(PASSWORD_REGISTER_TEXT_FIELD_NAME);
		this.surnameRegister.setName(SURNAME_REGISTER_TEXT_FIELD_NAME);
		this.nameRegister.setName(NAME_REGISTER_TEXT_FIELD_NAME);
		this.phoneNumberRegister.setName(P_N_REGISTER_TEXT_FIELD_NAME);
		this.currentPassword.setName(CONF_PASS_REGISTER_TEXT_FIELD_NAME);//settaggio del name delle JTextField utile per il controller
		
		this.save.setActionCommand(ConstantClass.SAVE_SETTINGS);
		this.save.addActionListener(new SettingsActionListener(this,this.mf));
		this.changePasswordButton.setActionCommand(ConstantClass.SHOW_PASSWORD_FIELD);
		this.changePasswordButton.addActionListener(new SettingsActionListener(this,this.mf));
		this.undoChangePassword.setActionCommand(ConstantClass.UNDO_ACTION_PASSWORD);
		this.undoChangePassword.addActionListener(new SettingsActionListener(this,this.mf));
		
		//componente 3: label del nome
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		this.gbc.insets = new Insets(15, 13, 0, 0);
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.anchor = GridBagConstraints.CENTER;
		this.add(title,gbc);
		
		//componente 3: label del nome
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.gbc.insets = new Insets(15, 13, 0, 0);
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.anchor = GridBagConstraints.CENTER;
		this.add(nameLabel,gbc);
		
		//componente 4: TextField del nome
		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.gbc.insets = new Insets(15, 13, 0, 0);
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.anchor = GridBagConstraints.CENTER;
		this.add(nameRegister,gbc);
		
		//componente 5:label del cognome
		this.gbc.gridx = 0;
		this.gbc.gridy = 2;
		this.gbc.insets = new Insets(15, 13, 0, 0);
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.anchor = GridBagConstraints.CENTER;
		this.add(surnameLabel,gbc);
		
		//componente 6: TextField del cognome
		this.gbc.gridx = 1;
		this.gbc.gridy = 2;
		this.gbc.insets = new Insets(15, 13, 0, 0);
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.anchor = GridBagConstraints.CENTER;
		this.add(surnameRegister,gbc);

		//component: bottone per cambiare le password
		this.gbc.gridx = 0;
		this.gbc.gridy = 3;
		this.add(changePasswordButton,gbc);
		
		//componente 9: Label della password
		this.gbc.gridx = 0;
		this.gbc.gridy = 4;
		this.add(currentPasswordLabel,gbc);
		
		//componente 10: TextField della password
		this.gbc.gridx = 1;
		this.gbc.gridy = 4;
		this.add(currentPassword,gbc);
		
		//componente 11: Label della conferma password
		this.gbc.gridx = 0;
		this.gbc.gridy = 5;
		this.add(newPasswordLabel,gbc);
		
		//componente 12: TextField della conferma password
		this.gbc.gridx = 1;
		this.gbc.gridy = 5;
		this.add(newPassword,gbc);
		
		//componente 11: Label della conferma password
		this.gbc.gridx = 0;
		this.gbc.gridy = 6;
		this.add(confirmPasswordLabel,gbc);

		//componente 12: TextField della conferma password
		this.gbc.gridx = 1;
		this.gbc.gridy = 6;
		this.add(confirmNewPassword,gbc);
		
		//component: bottone per cambiare le password
		this.gbc.gridx = 0;
		this.gbc.gridy = 7;
		this.add(undoChangePassword,gbc);
		
		//componente 15: Label del numero di telefono
		this.gbc.gridx = 0;
		this.gbc.gridy = 8;
		this.add(phoneNumberLabel,gbc);
		
		//componente 16: TextField del numero di telefono
		this.gbc.gridx = 1;
		this.gbc.gridy = 8;
		this.add(phoneNumberRegister,gbc);

		//componente 17: Label del giorno di nascita
		this.gbc.gridx = 0;
		this.gbc.gridy = 9;
		this.add(birthdayLabel,gbc);
		
		//componente 18: Aggiunta dataPicker
		this.gbc.gridx = 1;
		this.gbc.gridy = 9;
		this.add(dataPicker,gbc);
		
		//componente 19: Bottone di registrazione
		this.gbc.gridx = 1;
		this.gbc.gridy = 10;
		this.gbc.insets = new Insets(35, 13, 0, 0);
		this.gbc.gridwidth = GridBagConstraints.CENTER;
		this.add(save,gbc);
	
	}
	
	public void showChangePassword()
	{
		this.changePasswordButton.setVisible(false);//disabilita il pulsante per cambiare la passwrod
		//quando premuto il pulsante vengono visualizzati i campi per modificare la password
		this.newPasswordLabel.setVisible(true);
		this.confirmPasswordLabel.setVisible(true);
		this.currentPasswordLabel.setVisible(true);
		this.currentPassword.setVisible(true); 
		this.newPassword.setVisible(true); 
		this.confirmNewPassword.setVisible(true); 
		this.undoChangePassword.setVisible(true);
	}
	
	public void hiddenChangePassword()
	{
		this.newPasswordLabel.setVisible(false);
		this.confirmPasswordLabel.setVisible(false);
		this.currentPasswordLabel.setVisible(false);
		this.currentPassword.setVisible(false); 
		this.newPassword.setVisible(false); 
		this.confirmNewPassword.setVisible(false); 
		this.undoChangePassword.setVisible(false);
		this.changePasswordButton.setVisible(true);
	}
	
	//TODO nel caso si puo aggiungere anche il metodo per nascondere le impostaioni della password con un tasto annulla
	
	public String getTypeUser()
	{
		return SessionCheck.getInstance().getTypeUser();
	}
	
	public void setField(ArrayList<String> value) 
	{
		this.nameRegister.setText(value.get(0));
		this.surnameRegister.setText(value.get(1));		
		if(getTypeUser().equals("member"))
		{
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try
			{
				Date date=sdf.parse(value.get(2));	
				this.dataPicker.setDate(date);
			}
			catch(ParseException e)
			{
				addMessageToPanel(Message.getInstance().printErrorMsg("Error to Load date. Set it manually"));
			}		
		}
		else 
		{
			this.phoneNumberRegister.setText(value.get(2));
		}
	}
	
	
	public ArrayList<String> getFormData()
	{
		ArrayList<String> registerData = new ArrayList<>();
		registerData.add(this.surnameRegister.getText()); //0
		registerData.add(this.nameRegister.getText()); //1
		registerData.add(String.valueOf(this.currentPassword.getPassword())); //2
		registerData.add(String.valueOf(this.newPassword.getPassword())); //3
		registerData.add(String.valueOf(this.confirmNewPassword.getPassword())); //4
		registerData.add(this.phoneNumberRegister.getText());//5
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		registerData.add(sdf.format(this.dataPicker.getDate().getTime()));//6
		return registerData;
	}
	
	public void addMessageToPanel(JLabel msg)
	{
		this.msg = msg;
		gbc.gridx = 0;
		gbc.gridy = 11;
		this.add(this.msg,gbc);
		this.revalidate();
		this.repaint();
	}
	
	public void removeMessageToPanel()
	{

		gbc.gridx = 0;
		gbc.gridy = 11;
		this.remove(this.msg);
		this.revalidate();
		this.repaint();
	}
	
	public boolean isVisiblePassword()
	{
		return this.currentPassword.isVisible();
	}

	
}
