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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import progettoEsame.centropolisportivo.model.TempAlterRegistration;
import progettoEsame.centropolisportivo.view.actionListener.ConfirmAlterRegistrationActionListener;



public class ConfirmAlterRegistrationCard extends JPanel
{

	private JLabel nameMemberLabel;
	private JLabel nameMember;
	private JLabel registrationIdLabel;
	private JLabel registrationId;

	private JButton accept;
	private JButton deny;
	
	private GridBagConstraints gbc;
	
	private int idTempAlter;
	
	public ConfirmAlterRegistrationCard(TempAlterRegistration tmp)//TODO sistemare con variabile locale
	{
		
		this.nameMemberLabel = new JLabel(ConstantClass.NAME_MEMBER);
		this.nameMember = new JLabel(tmp.getMember().getEmail());/////valore
		this.registrationIdLabel = new JLabel(ConstantClass.ID_REGISTRATION);
		this.registrationId = new JLabel(String.valueOf(tmp.getRegistration().getId()));////valore
		
		this.accept=new JButton(ConstantClass.ACCEPT_BUTTON);
		this.deny=new JButton(ConstantClass.DENY_BUTTON);
		
		this.accept.setActionCommand(ConstantClass.ACCEPT_BUTTON_CMD);
		this.accept.addActionListener(new ConfirmAlterRegistrationActionListener(this,tmp));
		
		this.deny.setActionCommand(ConstantClass.DENY_BUTTON_CMD);
		this.deny.addActionListener(new ConfirmAlterRegistrationActionListener(this,tmp));
		this.idTempAlter=tmp.getId();
		
		//set del layout dei pannelli
		this.setLayout(new GridBagLayout());
		this.gbc = new GridBagConstraints();
		
		//label nome memeber richiesta
		this.gbc.gridx = 0;
		this.gbc.gridy = 0;	
		this.gbc.insets = new Insets(0,40,10,0);
		this.add(this.nameMemberLabel,gbc);
		
		//nome memeber richiesta
		this.gbc.gridx = 1;
		this.gbc.gridy = 0;
		this.gbc.insets = new Insets(0,40,10,0);
		this.add(this.nameMember,gbc);

		//label registration id
		this.gbc.gridx = 2;
		this.gbc.gridy = 0;
		this.gbc.insets = new Insets(0,40,10,0);
		this.add(this.registrationIdLabel,gbc);

		//registration id
		this.gbc.gridx = 3;
		this.gbc.gridy = 0;	
		this.gbc.insets = new Insets(0,40,10,0);
		this.add(registrationId,gbc);
		
		//bottone accetta
		this.gbc.gridx = 0;
		this.gbc.gridy = 1;
		this.gbc.insets = new Insets(0,40,10,0);
		this.add(this.accept,gbc);
		
		//bottone rifiuta
		this.gbc.gridx = 2;
		this.gbc.gridy = 1;
		this.gbc.insets = new Insets(0,40,10,0);
		this.add(this.deny,gbc);				
	}
	
	public int getId()
	{
		return idTempAlter;
	}
	
}
