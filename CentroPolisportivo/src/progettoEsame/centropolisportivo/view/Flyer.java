package progettoEsame.centropolisportivo.view;


import static progettoEsame.centropolisportivo.view.ConstantClass.NO_REGISTRATION_PROPOSAL;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import progettoEsame.centropolisportivo.view.actionListener.ActivityController;
import progettoEsame.centropolisportivo.view.actionListener.CenterManagerRegistrationProposalController;

public class Flyer extends JPanel
{
	private ArrayList<ActivityCard> allActivity;
	private GridBagConstraints gbc;
	
	public Flyer() 
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
			this.allActivity = ActivityController.getInstance().getAllActivityCard();
			if(allActivity.size() == 0)
			{
				JLabel none = new JLabel(NO_REGISTRATION_PROPOSAL);
				none.setSize(new Dimension(75, 100));
				this.add(none);
			}
			else
			{
				for(int i = 0;i<allActivity.size();i++)
				{
					gbc.gridy = i;
					mainPanel.add(allActivity.get(i),gbc);
				}
				this.add(scrollPane);
			}
		} catch (SQLException e) {
			// TODO invia messaggio di errore
			e.printStackTrace();
		}
	}
}