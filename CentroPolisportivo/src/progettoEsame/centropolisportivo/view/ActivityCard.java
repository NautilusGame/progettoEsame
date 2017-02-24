package progettoEsame.centropolisportivo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import progettoEsame.centropolisportivo.view.actionListener.ActivityCardActionListener;

public class ActivityCard extends JPanel 
{
	private JPanel descriptionPanel;
	private JScrollPane scr;
	private JLabel nameActivityLabel;
	private JLabel nameActivity;
	private JLabel infoActivityLabel;
	private JLabel priceActivityLabel;
	private JLabel priceActivity;
	private JLabel picActivity;
	
	private JTextArea infoActivity;
	
	private JButton register;
	
	private BufferedImage image;
	
	private GridBagConstraints gbc;
	
	public ActivityCard(ArrayList<String> valueField,int user,Template template)//TODO sistemare con variabile locale
	{
		//inzializzazione dei componenti
		this.descriptionPanel= new JPanel();
		
		this.nameActivityLabel = new JLabel(ConstantClass.NAME_ACTIVITY_LABEL);
		this.nameActivity = new JLabel(valueField.get(1));/////
		this.infoActivityLabel = new JLabel(ConstantClass.INFO_ACTIVITY_LABEL);
		this.priceActivityLabel = new JLabel(ConstantClass.PRICE_ACTIVITY_LABEL);
		this.priceActivity=new JLabel(valueField.get(3));/////
		
		this.infoActivity = new JTextArea(valueField.get(2));///
		
		this.register=new JButton(ConstantClass.REGISTER_BUTTON_ACTIVITY);
		
		//set dei componenti a livello grafico della infoActivity
		this.infoActivity.setPreferredSize(new Dimension(75,50));		
		this.infoActivity.setLineWrap(true); //va a capo con il testo automaticamente
		this.infoActivity.setEditable(false);
		Color defaultColor = this.getBackground();//per non fare sembrare l differenza con lo sfondo
		this.infoActivity.setBackground(defaultColor);
		this.scr = new JScrollPane(infoActivity);//rende la JText area scrollabile
        this.scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		

		//upload image
		try 
		{
			image = ImageIO.read(new File(valueField.get(4)));///valueField.get(4)
			
			picActivity= new JLabel(new ImageIcon(getScaledImage(image, 200, 150)));			
		} 
		catch (IOException ex) {
			System.out.println(ex.getMessage());	
		}
		
		//swich tra catalgo pubblico o meno
		if(user==0)
			this.register.setVisible(false);
		else if(user == 1)
		{
			this.register.setVisible(true);
			this.register.setName(valueField.get(0));
			this.register.setActionCommand(ConstantClass.ACTIVITY_REGISTER_BUTTON);
			this.register.addActionListener(new ActivityCardActionListener(template,this));
		}
		else if(user==2)
		{
			this.register.setVisible(true);
			this.register.setName(valueField.get(0));
			this.register.setActionCommand(ConstantClass.ACTIVITY_SCHEDULE_BUTTON);
			this.register.addActionListener(new ActivityCardActionListener(template,this));
		}
		
		
		//set del layout dei pannelli
		this.setLayout(new BorderLayout());
		this.descriptionPanel.setLayout(new GridBagLayout());
		this.gbc = new GridBagConstraints();
		
		//label nome activity
		this.gbc.gridx = 1;
		this.gbc.gridy = 0;
		this.gbc.insets = new Insets(15, 13, 0, 0); //padding dell'elemento
		this.gbc.fill = GridBagConstraints.HORIZONTAL; //riempie tutta la cella
		this.gbc.anchor = GridBagConstraints.CENTER; //lo centra		
		this.descriptionPanel.add(this.nameActivityLabel,gbc);
		
		//nome activity
		this.gbc.gridx = 2;
		this.gbc.gridy = 0;
		this.descriptionPanel.add(this.nameActivity,gbc);

		//label info activity
		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.descriptionPanel.add(this.infoActivityLabel,gbc);

		//info activity
		this.gbc.gridx = 1;
		this.gbc.gridy = 2;
		this.gbc.fill = GridBagConstraints.HORIZONTAL; //riempie tutta la cella
		this.gbc.ipady=40;//setta la altezza
		this.gbc.ipadx=100;//setta la larghezza
		this.gbc.gridwidth=2;//indica quante colonne puo occupare			
		this.descriptionPanel.add(scr,gbc);
		
		//label prezzo activity
		this.gbc.gridx = 1;
		this.gbc.gridy = 3;
		this.gbc.ipady= 0;
		this.gbc.ipadx= 0;
		this.gbc.gridwidth=1;
		this.descriptionPanel.add(this.priceActivityLabel,gbc);
		
		//prezzo activity
		this.gbc.gridx = 2;
		this.gbc.gridy = 3;
		this.descriptionPanel.add(this.priceActivity,gbc);
		
		//bottone per la registreazione
		this.gbc.gridx = 2;
		this.gbc.gridy = 4;
		this.descriptionPanel.add(this.register,gbc);
		
		//isnerimento dell'immagine
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;
	
		this.gbc.gridheight=5;
		this.descriptionPanel.add(this.picActivity,gbc);
		
		this.add(this.descriptionPanel,BorderLayout.CENTER);
		
				
	}
	
	private Image getScaledImage(Image srcImg, int w, int h)
	{
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
	
	public int getIdActivity()
	{
		return Integer.parseInt(this.register.getName());
	}

}
