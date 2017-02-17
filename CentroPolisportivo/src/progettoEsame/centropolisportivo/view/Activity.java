package progettoEsame.centropolisportivo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.*;

public class Activity extends JPanel 
{
	private JPanel descriptionPanel;
	
	private JLabel nameActivityLabel;
	private JLabel nameActivity;
	private JLabel infoActivityLabel;
	private JLabel priceActivityLabel;
	private JLabel priceActivity;
	private JLabel picActivity;
	
	private JTextArea infoActivity;
	
	private JButton register;
	
	private GridBagConstraints gbc;
	
	public Activity()
	{
		//inzializzazione dei componenti
		this.descriptionPanel= new JPanel();
		
		this.nameActivityLabel = new JLabel(ConstantClass.NAME_ACTIVITY_LABEL);
		this.nameActivity = new JLabel("asdfdsfsdfsdf");
		this.infoActivityLabel = new JLabel(ConstantClass.INFO_ACTIVITY_LABEL);
		this.priceActivityLabel = new JLabel(ConstantClass.PRICE_ACTIVITY_LABEL);
		this.priceActivity=new JLabel("222");
		this.picActivity = new JLabel(new ImageIcon("../image/imageName.png"));
		
		this.infoActivity = new JTextArea("saasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd");
		
		this.register=new JButton(ConstantClass.REGISTER_BUTTON_ACTIVITY);
		
		//set dei componenti a livello grafico
		this.infoActivity.setPreferredSize(new Dimension(20,30));		
		this.infoActivity.setSize(200,200);
		this.infoActivity.setEditable(false);
		Color defaultColor = this.getBackground();//per non fare sembrare l differenza con lo sfondo
		this.infoActivity.setBackground(defaultColor);
		
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
		this.descriptionPanel.add(this.nameActivityLabel,gbc);
		
		//nome activity
		this.gbc.gridx = 1;
		this.gbc.gridy = 0;
		this.descriptionPanel.add(this.nameActivity,gbc);

		//label info activity
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.descriptionPanel.add(this.infoActivityLabel,gbc);

		//info activity
		this.gbc.gridx = 0;
		this.gbc.gridy = 2;
		this.gbc.fill = GridBagConstraints.HORIZONTAL; //riempie tutta la cella
		this.gbc.ipady=40;//setta la altezza
		this.gbc.ipadx=100;//setta la larghezza
		this.gbc.gridwidth=2;//indica quante colonne puo occupare			
		this.descriptionPanel.add(this.infoActivity,gbc);
		
		//label prezzo activity
		this.gbc.gridx = 0;
		this.gbc.gridy = 3;
		this.gbc.ipady= 0;
		this.gbc.ipadx= 0;
		this.gbc.gridwidth=1;
		this.descriptionPanel.add(this.priceActivityLabel,gbc);
		
		//prezzo activity
		this.gbc.gridx = 1;
		this.gbc.gridy = 3;
		this.descriptionPanel.add(this.priceActivity,gbc);
		
		//bottone per la registreazione
		this.gbc.gridx = 1;
		this.gbc.gridy = 4;
		this.descriptionPanel.add(this.register,gbc);
		
		this.add(this.descriptionPanel,BorderLayout.CENTER);
		this.add(this.picActivity,BorderLayout.WEST);
				
	}

}
