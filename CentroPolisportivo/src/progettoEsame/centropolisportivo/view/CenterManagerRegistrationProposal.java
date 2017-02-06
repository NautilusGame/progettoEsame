package progettoEsame.centropolisportivo.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import progettoEsame.centropolisportivo.view.actionListener.CenterManagerRegistrationProposalController;

public class CenterManagerRegistrationProposal extends JPanel{
	
	private ArrayList<JPanel> unconfirmedUsers;
	private GridBagConstraints gbc;
	public CenterManagerRegistrationProposal() 
	{
		gbc = new GridBagConstraints();
		JPanel mainPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(mainPanel);
		scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setMinimumSize(new Dimension(500, 700));
        scrollPane.setPreferredSize(new Dimension(500, 700));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		try {
			this.unconfirmedUsers = CenterManagerRegistrationProposalController.getInstance().getUnconfirmedUserPanel();
			for(int i = 0;i<unconfirmedUsers.size();i++)
			{
				gbc.gridy = i;
				mainPanel.add(unconfirmedUsers.get(i),gbc);
			}
			this.add(scrollPane);
		} catch (SQLException e) {
			// TODO invia messaggio di errore
			e.printStackTrace();
		}
	}

}
