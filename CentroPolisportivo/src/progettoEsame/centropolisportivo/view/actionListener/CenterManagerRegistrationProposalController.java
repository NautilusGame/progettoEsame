package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import progettoEsame.centropolisportivo.business.CenterManagerRegistrationProposalBusiness;
import progettoEsame.centropolisportivo.model.Member;
import progettoEsame.centropolisportivo.model.Trainer;
import static progettoEsame.centropolisportivo.business.ConstantClass.*;
public class CenterManagerRegistrationProposalController {
	
	private static CenterManagerRegistrationProposalController instance;
	
	public static synchronized CenterManagerRegistrationProposalController getInstance()
	{
		if(instance == null)
			return new CenterManagerRegistrationProposalController();
		return instance;
	}
	
	public ArrayList<JPanel> getUnconfirmedUserPanel()throws SQLException
	{
		ArrayList<JPanel> unconfirmedUsers = new ArrayList<JPanel>();
		ArrayList<Member> tmpUnconfirmedMember = CenterManagerRegistrationProposalBusiness.getInstance().getAllUnconfirmedMember();
		ArrayList<Trainer> tmpUnconfirmedTrainer = CenterManagerRegistrationProposalBusiness.getInstance().getAllUnconfirmedTrainer();
		for(int i = 0;i<tmpUnconfirmedMember.size();i++)
		{
			JPanel tmpPanel = new JPanel();
			JButton acceptButton = new JButton(ACCEPT_BUTTON_TEXT);
			JButton declineButton = new JButton(DECLINE_BUTTON_TEXT);
			tmpPanel.setPreferredSize(new Dimension(450, 90));
			tmpPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			GridBagConstraints gbc = new GridBagConstraints();
			tmpPanel.setLayout(new GridBagLayout());
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.insets = new Insets(0, 20, 0, 0);
			tmpPanel.add(new JLabel("Name: " + tmpUnconfirmedMember.get(i).getName()),gbc);
			gbc.gridx = 1;
			gbc.gridy = 0;
			tmpPanel.add(new JLabel("Surname: " + tmpUnconfirmedMember.get(i).getSurname()),gbc);
			gbc.gridx = 0;
			gbc.gridy = 1;
			tmpPanel.add(new JLabel("Email: " + tmpUnconfirmedMember.get(i).getEmail()),gbc);
			gbc.gridx = 2;
			gbc.gridy = 0;
			gbc.insets = new Insets(4, 10, 0, 0);
			acceptButton.addActionListener(new CenterManagerRegistrationProposalActionListener());
			acceptButton.setActionCommand(tmpUnconfirmedMember.get(i).getEmail());
			acceptButton.setName(ACCEPT_BUTTON_MEMBER_NAME);
			tmpPanel.add(acceptButton, gbc);
			gbc.gridx = 3;
			gbc.gridy = 0;
			declineButton.addActionListener(new CenterManagerRegistrationProposalActionListener());
			declineButton.setActionCommand(tmpUnconfirmedMember.get(i).getEmail());
			declineButton.setName(DECLINE_BUTTON_MEMBER_NAME);
			tmpPanel.add(declineButton, gbc);
			unconfirmedUsers.add(tmpPanel);
		}
		for(int i = 0;i<tmpUnconfirmedTrainer.size();i++)
		{
			JPanel tmpPanel = new JPanel();
			JButton acceptButton = new JButton(ACCEPT_BUTTON_TEXT);
			JButton declineButton = new JButton(DECLINE_BUTTON_TEXT);
			tmpPanel.setPreferredSize(new Dimension(450, 90));
			tmpPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			GridBagConstraints gbc = new GridBagConstraints();
			tmpPanel.setLayout(new GridBagLayout());
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.insets = new Insets(4, 50, 0, 0);
			tmpPanel.add(new JLabel("Name: "+ tmpUnconfirmedTrainer.get(i).getName()),gbc);
			gbc.gridx = 1;
			gbc.gridy = 0;
			tmpPanel.add(new JLabel("Surname: " + tmpUnconfirmedTrainer.get(i).getSurname()),gbc);
			gbc.gridx = 0;
			gbc.gridy = 1;
			tmpPanel.add(new JLabel("Email: " + tmpUnconfirmedTrainer.get(i).getEmail()),gbc);
			gbc.gridx = 2;
			gbc.gridy = 0;
			gbc.insets = new Insets(4, 10, 0, 0);
			acceptButton.addActionListener(new CenterManagerRegistrationProposalActionListener());
			acceptButton.setActionCommand(tmpUnconfirmedTrainer.get(i).getEmail());
			acceptButton.setName(ACCEPT_BUTTON_TRAINER_NAME);
			tmpPanel.add(acceptButton, gbc);
			gbc.gridx = 3;
			gbc.gridy = 0;
			declineButton.addActionListener(new CenterManagerRegistrationProposalActionListener());
			declineButton.setActionCommand(tmpUnconfirmedTrainer.get(i).getEmail());
			declineButton.setName(DECLINE_BUTTON_TRAINER_NAME);
			tmpPanel.add(declineButton, gbc);
			unconfirmedUsers.add(tmpPanel);
		}
		return unconfirmedUsers;
	}

}
