package progettoEsame.centropolisportivo.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import progettoEsame.centropolisportivo.view.actionListener.EventCardActionListener;

public class EventCard extends JPanel{

	private JPanel descriptionPanel;
	private JLabel nameEventLabel; 
	private JLabel nameEvent;
	private JLabel startEventLabel; 
	private JLabel startEvent;
	private JLabel finishEventLabel; 
	private JLabel finishEvent;
	private JLabel typeEventLabel; 
	private JLabel typeEvent;
	private JLabel priceEventLabel;
	private JLabel priceEvent;

	private JButton register;


	private GridBagConstraints gbc;

	public EventCard(ArrayList<String> valueField,int user,Template template)//TODO sistemare con variabile locale
	{
		//inzializzazione dei componenti
		this.descriptionPanel= new JPanel();

		this.nameEventLabel = new JLabel(ConstantClass.EVENT_CARD_NAME_ACTIVITY_LABEL);
		this.nameEvent = new JLabel(valueField.get(1));
		this.priceEventLabel = new JLabel(ConstantClass.EVENT_CARD_PRICE_ACTIVITY_LABEL);
		this.priceEvent=new JLabel(valueField.get(4));
		this.startEventLabel = new JLabel(ConstantClass.EVENT_CARD_START_ACTIVITY_LABEL);
		this.startEvent=new JLabel(valueField.get(2));
		this.finishEventLabel = new JLabel(ConstantClass.EVENT_CARD_FINISH_ACTIVITY_LABEL);
		this.finishEvent=new JLabel(valueField.get(3));
		this.typeEventLabel = new JLabel(ConstantClass.EVENT_CARD_TYPE_ACTIVITY_LABEL);
		this.typeEvent=new JLabel(valueField.get(5));


		this.register=new JButton(ConstantClass.EVENT_CARD_OPEN_BUTTON);

		//set dei componenti a livello grafico della infoActivity



		//swich tra catalgo pubblico o meno
		if(user==0)
			this.register.setVisible(false);
		else if(user == 1)
		{
			this.register.setVisible(true);
			this.register.setName(valueField.get(0));
			this.register.setActionCommand(ConstantClass.ACTIVITY_REGISTER_BUTTON);
			this.register.addActionListener(new EventCardActionListener(template,this));
		}


		//set del layout dei pannelli
		this.setLayout(new BorderLayout());
		this.descriptionPanel.setLayout(new GridBagLayout());
		this.gbc = new GridBagConstraints();

		//label nome activity
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
		this.gbc.insets = new Insets(15, 13, 0, 0); //padding dell'elemento
		this.gbc.fill = GridBagConstraints.HORIZONTAL; //riempie tutta la cella
		this.gbc.anchor = GridBagConstraints.CENTER; //lo centra		
		this.descriptionPanel.add(this.nameEventLabel,gbc);

		//nome activity
		this.gbc.gridx = 1;
		this.gbc.gridy = 0;
		this.descriptionPanel.add(this.nameEvent,gbc);



		//label prezzo activity
		this.gbc.gridx = 0;
		this.gbc.gridy = 2;
		this.gbc.ipady= 0;
		this.gbc.ipadx= 0;
		this.gbc.gridwidth=1;
		this.descriptionPanel.add(this.startEventLabel,gbc);

		//prezzo activity
		this.gbc.gridx = 1;
		this.gbc.gridy = 2;
		this.descriptionPanel.add(this.startEvent,gbc);

		//label prezzo activity
		this.gbc.gridx = 0;
		this.gbc.gridy = 3;
		this.gbc.ipady= 0;
		this.gbc.ipadx= 0;
		this.gbc.gridwidth=1;
		this.descriptionPanel.add(this.finishEventLabel,gbc);

		//prezzo activity
		this.gbc.gridx = 1;
		this.gbc.gridy = 3;
		this.descriptionPanel.add(this.finishEvent,gbc);

		//label prezzo activity
		if(valueField.get(6) == "0")
		{
			this.gbc.gridx = 0;
			this.gbc.gridy = 4;
			this.gbc.ipady= 0;
			this.gbc.ipadx= 0;
			this.gbc.gridwidth=1;
			this.descriptionPanel.add(this.priceEventLabel,gbc);

			//prezzo activity
			this.gbc.gridx = 1;
			this.gbc.gridy = 4;
			this.descriptionPanel.add(this.priceEvent,gbc);
		}
		else
		{
			this.gbc.gridx = 0;
			this.gbc.gridy = 4;
			this.gbc.ipady= 0;
			this.gbc.ipadx= 0;
			this.gbc.gridwidth=1;
			this.descriptionPanel.add(this.priceEventLabel,gbc);

			//prezzo activity
			this.gbc.gridx = 1;
			this.gbc.gridy = 4;
			this.descriptionPanel.add(new JLabel("Free"),gbc);
		}

		//label prezzo activity
		this.gbc.gridx = 0;
		this.gbc.gridy = 5;
		this.gbc.ipady= 0;
		this.gbc.ipadx= 0;
		this.gbc.gridwidth=1;
		this.descriptionPanel.add(this.typeEventLabel,gbc);

		//prezzo activity
		this.gbc.gridx = 1;
		this.gbc.gridy = 5;
		this.descriptionPanel.add(this.typeEvent,gbc);
		//bottone per la registreazione
		this.gbc.gridx = 1;
		this.gbc.gridy = 6;
		this.descriptionPanel.add(this.register,gbc);


		this.add(this.descriptionPanel,BorderLayout.CENTER);


	}

	public int getIdEvent()
	{
		return Integer.parseInt(this.register.getName());
	}

}
