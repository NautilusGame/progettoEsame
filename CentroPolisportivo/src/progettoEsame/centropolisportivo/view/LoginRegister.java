package progettoEsame.centropolisportivo.view;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;

import java.awt.Color;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import com.toedter.calendar.JCalendar;
import progettoEsame.centropolisportivo.view.actionListener.LoginRegisterActionListener;
import progettoEsame.centropolisportivo.view.actionListener.LoginRegisterFocusListener;
import progettoEsame.centropolisportivo.view.actionListener.LoginRegisterMouseListener;

public class LoginRegister extends JPanel{
	
	private MainFrame mf;
	private JPanel loginPanel;
	private JPanel registerPanel;  //pannelli che si sovrappongono al "this", rispettivamente uno per il login e l'altro per il register
	
	
	private JTextField usernameLogin; //username per il login
	private JPasswordField passwordLogin; // password per il login // username per il register
	private JPasswordField passwordRegister; //password per il register
	private JTextField emailRegister; //email per il register
	private JTextField nameRegister;//nome utente per il register
	private JTextField surnameRegister;//cognome utente per il register
	private JPasswordField confirmPasswordRegister;//conferma della password per il register
	private JTextField phoneNumberRegister;//numero di telefono per il trainer(a scomparsa)

	
	private JButton login; //bottone di login
	private JButton register; //bottone di registrazione
	
	
	private JLabel passwordLabel; //label password nel pannello password //label username nel pannello registrazione
	private JLabel passwordRegisterLabel; //label password nel pannello registrazione 
	private JLabel emailLabel; //label email nel pannello registrazione
	private JLabel registerLabel; //label CLICCABILE che permette di switchare nel pannello di registrazione
	private JLabel loginLabel; //label CLICCABILE che permette di switchare nel pannello di login
	private JLabel nameLabel; //label nome utente nel pannello di registrazione
	private JLabel surnameLabel; //label cognome nel pannello di registrazione
	private JLabel birthdayLabel; //...
	private JLabel confirmPasswordLabel; //label di conferma della password nel pannello di registrazione
	private JLabel phoneNumberLabel; 
	
	
	private JRadioButton registeredUser; //radio button "member"
	private JRadioButton trainerUser; //radio button "trainer"
	private ButtonGroup userType;
	
	
	private GridBagConstraints gbc; //vincolo per il gridBagLayout
	private JCalendar dataPicker;
	
	
	public LoginRegister(MainFrame mf)
	{
		
		this.mf = mf; //ho passato come parametro il mainframe per switchare tra i pannelli
		this.setLayout(new GridBagLayout());//set del layout al pannello "this"
		
		
		gbc = new GridBagConstraints();
		
		/*_______________LOGIN PANEL______________*/
		
		
		this.loginPanel = new JPanel();
		this.registerPanel = new JPanel();
		this.loginPanel.setLayout(new GridBagLayout());
		this.registerPanel.setLayout(new GridBagLayout());
		
		this.usernameLogin = new JTextField(TEXT_FIELD_DIMENSION);
		this.passwordLogin = new JPasswordField(TEXT_FIELD_DIMENSION);
		
		this.login = new JButton(LOGIN_BUTTON_TEXT);
		this.login.setActionCommand(LOGIN_BUTTON_ACTION_CMD);
		this.login.addActionListener(new LoginRegisterActionListener(this,this.mf));
		
		this.emailLabel = new JLabel(EMAIL_LABEL_TEXT);
		this.passwordLabel = new JLabel(PASSWORD_LABEL_TEXT);
		this.registerLabel = new JLabel(REGISTER_LABEL_TEXT);
		
		this.usernameLogin.setText(DEFAULT_TEXT_USERNAME_FIELD);//settaggio testo di default
		
		this.usernameLogin.setForeground(Color.GRAY);
		this.passwordLogin.setForeground(Color.GRAY);
		this.registerLabel.setForeground(Color.GRAY);//settaggio colore
		
		this.registerLabel.setName(REGISTER_BUTTON_NAME);
		this.usernameLogin.setName(USERNAME_LOGIN_TEXT_FIELD_NAME);
		this.passwordLogin.setName(PASSWORD_LOGIN_TEXT_FIELD_NAME);//settaggio nomi dei componeneti, utile per gestione nel controller
		
		//TODO setMinimumSize setMaximumSize per gestire l'auto resize degli oggetti
		
		Font font = registerLabel.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		this.registerLabel.setFont(font.deriveFont(attributes));//sottolinea label di registrazione(come se fosse un link)
		
		this.registerLabel.addMouseListener(new LoginRegisterMouseListener(this));
		this.usernameLogin.addFocusListener(new LoginRegisterFocusListener(this));
		this.passwordLogin.addFocusListener(new LoginRegisterFocusListener(this));
		
		
		//inizio aggiunta dei componenti con settaggio del vincolo del gridBagLayout
		
		//componente 1:label dello username
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		this.gbc.insets = new Insets(15, 13, 0, 0); //padding dell'elemento
		this.gbc.fill = GridBagConstraints.HORIZONTAL; //riempie tutta la cella
		this.gbc.anchor = GridBagConstraints.CENTER; //lo centra
		this.loginPanel.add(emailLabel,gbc);
		
		//componente 2:text field dello username
		this.gbc.gridx = 1;
		this.gbc.gridy = 0;
		this.loginPanel.add(usernameLogin,gbc);

		//componente 3: label della password
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.loginPanel.add(passwordLabel,gbc);
		
		//componente 4: textfield della password
		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.loginPanel.add(passwordLogin,gbc);
		
		//componente 5: label cliccabile per switchare al pannello di registrazione
		this.gbc.gridx = 0;
		this.gbc.gridy = 2;
		this.gbc.insets = new Insets(35, 13, 5, 0);
		this.loginPanel.add(registerLabel,gbc);
		
		//componente 6: pulsante login
		this.gbc.gridx = 1;
		this.gbc.gridy = 2;
		this.gbc.insets = new Insets(35, 13, 0, 0);
		this.gbc.gridwidth = GridBagConstraints.CENTER;
		this.loginPanel.add(login,gbc);

		//aggiunta del pannello login al "this"
		this.add(loginPanel);
		
		
		/*_______________REGISTER PANEL______________*/
		
		
		this.gbc = new GridBagConstraints();//inizializzazione del vincolo
		
		this.nameLabel = new JLabel(NAME_LABEL_TEXT);
		this.surnameLabel = new JLabel(SURNAME_LABEL_TEXT);
		this.loginLabel = new JLabel(LOGIN_BUTTON_TEXT);
		this.birthdayLabel = new JLabel(BIRTHDAY_LABEL_TEXT);
		this.confirmPasswordLabel = new JLabel(CONFIRM_PASSWORD_LABEL_TEXT);
		this.phoneNumberLabel = new JLabel(P_N_LABEL_TEXT);
		this.emailLabel = new JLabel(EMAIL_LABEL_TEXT);
		this.passwordRegisterLabel = new JLabel(PASSWORD_LABEL_TEXT); //inizializzazione label
		
		this.nameRegister = new JTextField(TEXT_FIELD_DIMENSION);
		this.surnameRegister = new JTextField(TEXT_FIELD_DIMENSION);
		this.confirmPasswordRegister = new JPasswordField(TEXT_FIELD_DIMENSION);
		this.phoneNumberRegister = new JTextField(TEXT_FIELD_DIMENSION);
		this.passwordRegister = new JPasswordField(TEXT_FIELD_DIMENSION);
		this.emailRegister = new JTextField(TEXT_FIELD_DIMENSION);//inizializzazione jTextField
		
		this.dataPicker = new JCalendar();
		this.register = new JButton(REGISTER_BUTTON_TEXT);
		this.registeredUser = new JRadioButton(MEMBER_RADIOBUTTON);
		this.trainerUser = new JRadioButton(TRAINER_RADIOBUTTON);
		this.userType = new ButtonGroup();
		
		this.loginLabel.setForeground(Color.GRAY);
		this.passwordRegister.setForeground(Color.GRAY);
		this.emailRegister.setForeground(Color.GRAY);
		this.surnameRegister.setForeground(Color.GRAY);
		this.nameRegister.setForeground(Color.GRAY);
		this.confirmPasswordRegister.setForeground(Color.GRAY);
		this.phoneNumberRegister.setForeground(Color.GRAY);//setting del colore delle JTextField
		
		
		//this.passwordRegister.setText(DEFAULT_TEXT_PASSWORD_FIELD);
		this.emailRegister.setText(DEFAULT_TEXT_EMAIL_FIELD);
		this.nameRegister.setText(DEFAULT_TEXT_NAME_FIELD);
		this.phoneNumberRegister.setText(DEFAULT_TEXT_P_N_FIELD);
		this.surnameRegister.setText(DEFAULT_TEXT_SURNAME_FIELD);
		//this.confirmPasswordRegister.setText(DEFAULT_TEXT_CONF_PASSWORD_FIELD);//settaggio del testo di default delle JTextField
		
		
		this.loginLabel.setName(LOGIN_BUTTON_NAME);
		this.passwordRegister.setName(PASSWORD_REGISTER_TEXT_FIELD_NAME);
		this.emailRegister.setName(EMAIL_REGISTER_TEXT_FIELD_NAME);
		this.surnameRegister.setName(SURNAME_REGISTER_TEXT_FIELD_NAME);
		this.nameRegister.setName(NAME_REGISTER_TEXT_FIELD_NAME);
		this.phoneNumberRegister.setName(P_N_REGISTER_TEXT_FIELD_NAME);
		this.confirmPasswordRegister.setName(CONF_PASS_REGISTER_TEXT_FIELD_NAME);//settaggio del name delle JTextField utile per il controller
		
		font = loginLabel.getFont();
		attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		this.loginLabel.setFont(font.deriveFont(attributes));//settaggio del font "Underline" per la login label che permette di switchare al pannello di login
		
		
		this.loginLabel.addMouseListener(new LoginRegisterMouseListener(this));
		this.register.addActionListener(new LoginRegisterActionListener(this,this.mf));
		this.registeredUser.addActionListener(new LoginRegisterActionListener(this,this.mf));
		this.trainerUser.addActionListener(new LoginRegisterActionListener(this,this.mf));
		this.passwordRegister.addFocusListener(new LoginRegisterFocusListener(this));
		this.emailRegister.addFocusListener(new LoginRegisterFocusListener(this));
		this.nameRegister.addFocusListener(new LoginRegisterFocusListener(this));
		this.surnameRegister.addFocusListener(new LoginRegisterFocusListener(this));
		this.confirmPasswordRegister.addFocusListener(new LoginRegisterFocusListener(this));
		this.phoneNumberRegister.addFocusListener(new LoginRegisterFocusListener(this)); //settaggio dei listener
		
		this.register.setActionCommand(REGISTER_BUTTON_ACTION_CMD);
		this.registeredUser.setActionCommand(MEMBER_RADIOBUTTON_ACTION_CMD);
		this.trainerUser.setActionCommand(TRAINER_RADIOBUTTON_ACTION_CMD);//settaggio degli action command utile per il controller
		
		this.registeredUser.setSelected(true); //radio button pre selezionato
		
		this.trainerUser.setMnemonic(KeyEvent.VK_T);
		this.registeredUser.setMnemonic(KeyEvent.VK_M); //Permette di selezionare i radio button con le combinazioni di tasti Alt+T(Per il trainer) e Alt+M(Per il member)
		
		this.userType.add(registeredUser);
		this.userType.add(trainerUser); //aggiunge i radio button al button group
		
		//TODO setMinimumSize setMaximumSize per gestire l'auto resize degli oggetti
		/*this.username.requestFocusInWindow();
		this.password.requestFocusInWindow();*/
		
		this.makeInvisiblePhoneNumberField(); // rende il campo phone number invisibile
		
		//inizio aggiunta dei componenti al pannello di registrazione con settaggio del vincolo
		
		//componenete 1: radio button "Member"
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		this.gbc.insets = new Insets(15, 13, 0, 0);
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.anchor = GridBagConstraints.CENTER;
		this.registerPanel.add(registeredUser,gbc);
		
		//componenete 2:radio button "trainer"
		this.gbc.gridx = 1;
		this.gbc.gridy = 0;
		this.gbc.insets = new Insets(15, 13, 0, 0);
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.anchor = GridBagConstraints.CENTER;
		this.registerPanel.add(trainerUser,gbc);
		
		//componente 3: label del nome
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.gbc.insets = new Insets(15, 13, 0, 0);
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.anchor = GridBagConstraints.CENTER;
		this.registerPanel.add(nameLabel,gbc);
		
		//componente 4: TextField del nome
		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.gbc.insets = new Insets(15, 13, 0, 0);
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.anchor = GridBagConstraints.CENTER;
		this.registerPanel.add(nameRegister,gbc);
		
		//componente 5:label del cognome
		this.gbc.gridx = 0;
		this.gbc.gridy = 2;
		this.gbc.insets = new Insets(15, 13, 0, 0);
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.anchor = GridBagConstraints.CENTER;
		this.registerPanel.add(surnameLabel,gbc);
		
		//componente 6: TextField del cognome
		this.gbc.gridx = 1;
		this.gbc.gridy = 2;
		this.gbc.insets = new Insets(15, 13, 0, 0);
		this.gbc.fill = GridBagConstraints.HORIZONTAL;
		this.gbc.anchor = GridBagConstraints.CENTER;
		this.registerPanel.add(surnameRegister,gbc);

		//componente 9: Label della password
		this.gbc.gridx = 0;
		this.gbc.gridy = 3;
		this.registerPanel.add(passwordRegisterLabel,gbc);
		
		//componente 10: TextField della password
		this.gbc.gridx = 1;
		this.gbc.gridy = 3;
		this.registerPanel.add(passwordRegister,gbc);
		
		//componente 11: Label della conferma password
		this.gbc.gridx = 0;
		this.gbc.gridy = 4;
		this.registerPanel.add(confirmPasswordLabel,gbc);
		
		//componente 12: TextField della conferma password
		this.gbc.gridx = 1;
		this.gbc.gridy = 4;
		this.registerPanel.add(confirmPasswordRegister,gbc);
		
		//componente 13: Label della email
		this.gbc.gridx = 0;
		this.gbc.gridy = 5;
		this.registerPanel.add(emailLabel,gbc);
		
		//componente 14: TextField della email
		this.gbc.gridx = 1;
		this.gbc.gridy = 5;
		this.registerPanel.add(emailRegister,gbc);
		
		//componente 15: Label del numero di telefono
		this.gbc.gridx = 0;
		this.gbc.gridy = 6;
		this.registerPanel.add(phoneNumberLabel,gbc);
		
		//componente 16: TextField del numero di telefono
		this.gbc.gridx = 1;
		this.gbc.gridy = 6;
		this.registerPanel.add(phoneNumberRegister,gbc);

		//componente 17: Label del giorno di nascita
		this.gbc.gridx = 0;
		this.gbc.gridy = 7;
		this.registerPanel.add(birthdayLabel,gbc);
		
		//componente 18: Aggiunta dataPicker
		this.gbc.gridx = 1;
		this.gbc.gridy = 7;
		this.registerPanel.add(dataPicker,gbc);
		
		//componente 19: Bottone di registrazione
		this.gbc.gridx = 1;
		this.gbc.gridy = 8;
		this.gbc.insets = new Insets(35, 13, 0, 0);
		this.gbc.gridwidth = GridBagConstraints.CENTER;
		this.registerPanel.add(register,gbc);

		//componente 20: Label del login che permette di switchare al pannello di login
		this.gbc.gridx = 0;
		this.gbc.gridy = 8;
		this.gbc.insets = new Insets(35, 13, 0, 0);
		this.gbc.gridwidth = GridBagConstraints.CENTER;
		this.registerPanel.add(loginLabel,gbc);
		
		this.makeRegisterPanelInvisible();//rende il pannello di registrazione invisibile
		
		
		this.add(registerPanel);//aggiunge il pannello di registrazione al pannello "This"
		
		
		
	}
	
	public void makeLoginPanelVisible()
	{
		this.loginPanel.setVisible(true);
	}
	
	public void makeRegisterPanelVisible()
	{
		this.registerPanel.setVisible(true);
	}
	
	public void makeLoginPanelInvisible()
	{
		this.loginPanel.setVisible(false);
	}
	
	public void makeRegisterPanelInvisible()
	{
		this.registerPanel.setVisible(false);
	}
	
	public void makeInvisiblePhoneNumberField()
	{
		this.phoneNumberLabel.setVisible(false);
		this.phoneNumberRegister.setVisible(false);
	}
	public void makeVisiblePhoneNumberField()
	{
		this.phoneNumberLabel.setVisible(true);
		this.phoneNumberRegister.setVisible(true);
	}
	
	public ArrayList<String> getLoginData()
	{
		ArrayList<String> loginData = new ArrayList<>();
		loginData.add(this.usernameLogin.getText());
		loginData.add(String.valueOf(this.passwordLogin.getPassword()));
		return loginData;
	}
	
	public ArrayList<String> getRegisterData()
	{

		ArrayList<String> registerData = new ArrayList<>();
		registerData.add(this.surnameRegister.getText()); //0
		registerData.add(this.nameRegister.getText()); //1
		registerData.add(String.valueOf(this.passwordRegister.getPassword())); //2
		registerData.add(String.valueOf(this.confirmPasswordRegister.getPassword())); //3
		registerData.add(this.phoneNumberRegister.getText());//4
		registerData.add(this.emailRegister.getText());//5
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		registerData.add(sdf.format(this.dataPicker.getDate().getTime()));//6
		registerData.add(this.userType.getSelection().getActionCommand());//7
		return registerData;
	}
	
	public void addMessageToPanel(JLabel msg)
	{
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(msg,gbc);
		this.revalidate();
		this.repaint();
	}
	
	public void removeMessageToPanel()
	{

		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(new JLabel(""),gbc);
		this.revalidate();
		this.repaint();
	}

}
