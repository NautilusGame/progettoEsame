package progettoEsame.centropolisportivo.view;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.toedter.calendar.JCalendar;

import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.view.actionListener.SessionCheck;

public class Settings extends JPanel {

	private MainFrame mf;

	private JTextField nameRegister;//nome utente per il register
	private JTextField surnameRegister;//cognome utente per il register
	private JPasswordField currentPassword;//conferma della password per il register
	private JPasswordField newPassword; 
	private JPasswordField confirmNewPassword;//conferma della password per il register
	private JTextField phoneNumberRegister;//numero di telefono per il trainer(a scomparsa)

	
	private JButton save; //bottone di registrazione
	private JButton changePasswordButton;
	
	private JLabel nameLabel; //label nome utente nel pannello di registrazione
	private JLabel surnameLabel; //label cognome nel pannello di registrazione
	private JLabel birthdayLabel; //...
	private JLabel currentPasswordLabel; 
	private JLabel confirmPasswordLabel; //label di conferma della password nel pannello di registrazione
	private JLabel newPasswordLabel;
	private JLabel phoneNumberLabel; 
	private JLabel title; 
	
	
	
	private GridBagConstraints gbc; //vincolo per il gridBagLayout
	private JCalendar dataPicker;
	
	
	public Settings (MainFrame mf){
		
		this.mf=mf;
		
		this.setLayout(new GridBagLayout());//set del layout al pannello "this"
		this.gbc = new GridBagConstraints();//inizializzazione del vincolo
		
		//FIXME cancellare sessioen
		Session s=new Session();
		s.getInstance().createSession("s","member");
		
		//inizializzazione degli elementi
		this.title=new JLabel(TITLE_SETTINGS);
		this.nameLabel = new JLabel(NAME_LABEL_TEXT);
		this.surnameLabel = new JLabel(SURNAME_LABEL_TEXT);
		this.birthdayLabel = new JLabel(BIRTHDAY_LABEL_TEXT);
		this.phoneNumberLabel = new JLabel(P_N_LABEL_TEXT);		
		this.newPasswordLabel = new JLabel(NEW_PASSWORD_LABEL_TEXT);
		this.confirmPasswordLabel = new JLabel(CONFIRM_PASSWORD_LABEL_TEXT);
		this.currentPasswordLabel = new JLabel(CURRENT_PASSWORD_LABEL_TEXT); //inizializzazione label
		
		this.nameRegister = new JTextField(TEXT_FIELD_DIMENSION);
		this.surnameRegister = new JTextField(TEXT_FIELD_DIMENSION);
		this.phoneNumberRegister = new JTextField(TEXT_FIELD_DIMENSION);
		
		this.currentPassword = new JPasswordField(TEXT_FIELD_DIMENSION);
		this.newPassword = new JPasswordField(TEXT_FIELD_DIMENSION);
		this.confirmNewPassword = new JPasswordField(TEXT_FIELD_DIMENSION);		
		
		this.dataPicker = new JCalendar();
		this.save = new JButton(BUTTON_SAVE_SETTINGS);
		this.changePasswordButton = new JButton(BUTTON_CHANGE_PASSWORD);
		
		//sett dei cambi comuni delle password che devono essere nascosti
		this.newPasswordLabel.setVisible(false);
		this.confirmPasswordLabel.setVisible(false);
		this.currentPasswordLabel.setVisible(false);
		this.currentPassword.setVisible(false); 
		this.newPassword.setVisible(false); 
		this.confirmNewPassword.setVisible(false); 
		this.dataPicker.setVisible(false);
		
		if (this.getTypeUser().equals("member")){
			this.dataPicker.setVisible(true);
			this.birthdayLabel.setVisible(true);
			this.phoneNumberLabel.setVisible(false);
			this.phoneNumberRegister.setVisible(false);
		}
		else if (this.getTypeUser().equals("trainer")){
			this.dataPicker.setVisible(false);
			this.birthdayLabel.setVisible(false);
		}
		else{
			this.dataPicker.setVisible(false);
			this.birthdayLabel.setVisible(false);
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
		
		//componente 15: Label del numero di telefono
		this.gbc.gridx = 0;
		this.gbc.gridy = 7;
		this.add(phoneNumberLabel,gbc);
		
		//componente 16: TextField del numero di telefono
		this.gbc.gridx = 1;
		this.gbc.gridy = 7;
		this.add(phoneNumberRegister,gbc);

		//componente 17: Label del giorno di nascita
		this.gbc.gridx = 0;
		this.gbc.gridy = 8;
		this.add(birthdayLabel,gbc);
		
		//componente 18: Aggiunta dataPicker
		this.gbc.gridx = 1;
		this.gbc.gridy = 8;
		this.add(dataPicker,gbc);
		
		//componente 19: Bottone di registrazione
		this.gbc.gridx = 1;
		this.gbc.gridy = 9;
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
	}
	
	//TODO nel caso si puo aggiungere anche il metodo per nascondere le impostaioni della password con un tasto annulla
	
	public String getTypeUser(){
		return SessionCheck.getInstance().getTypeUser();
	}
	
	public void setField(ArrayList<String> valueField)
	{
		this.nameRegister.setText("");
		this.surnameRegister.setText("");
		this.currentPassword.setText("");
		if(getTypeUser().equals("member"))
		{
			//FIXME sistemare data
			//this.dataPicker.setDate();
		}
		else 
		{
			this.phoneNumberRegister.setText("");
		}
	}
	
	
}
