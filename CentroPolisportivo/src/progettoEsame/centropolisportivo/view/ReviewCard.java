package progettoEsame.centropolisportivo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import progettoEsame.centropolisportivo.view.actionListener.ActivityCardActionListener;

public class ReviewCard extends JPanel 
{
	private JPanel descriptionPanel;
	private JScrollPane scr;
	
	private JLabel nameMemberLabel;
	private JLabel nameMember;
	private JLabel textReviewLabel;
	private JLabel rateLabel;
	private JLabel rate;
	
	private JTextArea textReview;
	
	private GridBagConstraints gbc;
	
	public ReviewCard(ArrayList<String> valueField)
	{
		//inzializzazione dei componenti
		this.descriptionPanel= new JPanel();
		
		this.nameMemberLabel = new JLabel(ConstantClass.NAME_MEMBER_LABEL);
		this.nameMember = new JLabel(valueField.get(0));//TODO settare i campi
		this.textReviewLabel = new JLabel(ConstantClass.REVIEW_LABEL);
		this.rateLabel = new JLabel(ConstantClass.RATE_LABEL);
		this.rate=new JLabel(valueField.get(2));
		
		this.textReview = new JTextArea(valueField.get(1));//TODO settare i campi
		
		//set dei componenti a livello grafico della infoActivity
		this.textReview.setPreferredSize(new Dimension(75,50));		
		this.textReview.setLineWrap(true); //va a capo con il testo automaticamente
		this.textReview.setEditable(false);
		Color defaultColor = this.getBackground();//per non fare sembrare l differenza con lo sfondo
		this.textReview.setBackground(defaultColor);
		this.scr = new JScrollPane(textReview);//rende la JText area scrollabile
        this.scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
		//set del layout dei pannelli
		this.setLayout(new BorderLayout());
		this.descriptionPanel.setLayout(new GridBagLayout());
		this.gbc = new GridBagConstraints();
		
		this.gbc.ipadx=70;
		//label nome member
		this.gbc.gridx = 1;
		this.gbc.gridy = 0;
		this.gbc.insets = new Insets(15, 13, 0, 0); //padding dell'elemento
		this.gbc.fill = GridBagConstraints.HORIZONTAL; //riempie tutta la cella
		this.gbc.anchor = GridBagConstraints.CENTER; //lo centra		
		this.descriptionPanel.add(this.nameMemberLabel,gbc);
		
		//nome member
		this.gbc.gridx = 2;
		this.gbc.gridy = 0;
		this.descriptionPanel.add(this.nameMember,gbc);
		
		//label rate 
		this.gbc.gridx = 1;
		this.gbc.gridy = 3;
		this.descriptionPanel.add(this.rateLabel,gbc);

		//rate
		this.gbc.gridx = 2;
		this.gbc.gridy = 3;
		this.descriptionPanel.add(this.rate,gbc);
		
		//label text Review
		this.gbc.gridx = 1;
		this.gbc.gridy = 1;
		this.descriptionPanel.add(this.textReviewLabel,gbc);

		//text Review
		this.gbc.gridx = 1;
		this.gbc.gridy = 2;
		this.gbc.fill = GridBagConstraints.HORIZONTAL; //riempie tutta la cella
		this.gbc.ipady=40;//setta la altezza
		this.gbc.ipadx=100;//setta la larghezza
		this.gbc.gridwidth=2;//indica quante colonne puo occupare			
		this.descriptionPanel.add(scr,gbc);
		
		this.add(this.descriptionPanel,BorderLayout.CENTER);
		
				
	}
}
