package progettoEsame.centropolisportivo.view;

import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import progettoEsame.centropolisportivo.view.actionListener.AcceptRegistrationProposalController;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;
import static progettoEsame.centropolisportivo.view.ConstantClass.*;
public class AcceptRegistrationProposal extends JPanel{

	private ArrayList<JPanel> unconfirmedUsers;
	private GridBagConstraints gbc;
	private JLabel msg;
	private JPanel mainPanel;
	private JScrollPane scrollPane;
	
	public AcceptRegistrationProposal() 
	{
		this.msg = new JLabel();
		gbc = new GridBagConstraints();
		this.mainPanel = new JPanel();
		this.scrollPane = new JScrollPane(mainPanel);
		scrollPane.setLayout(new ScrollPaneLayout());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setMinimumSize(new Dimension(500, 700));
		scrollPane.setPreferredSize(new Dimension(500, 700));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.paintPanel();
	}
	public void addMessageToPanel(JLabel msg)
	{
		this.msg = msg;
		gbc.gridx = 0;
		gbc.gridy = 5;
		this.add(msg,gbc);
		this.revalidate();
		this.repaint();
	}

	public void removeMessageToPanel()
	{

		gbc.gridx = 0;
		gbc.gridy = 5;
		this.remove(this.msg);
		this.revalidate();
		this.repaint();
	}

	public void paintPanel()
	{
		this.mainPanel.removeAll();
		this.unconfirmedUsers = AcceptRegistrationProposalController.getInstance().getUnconfirmedUserPanel(this);
		if(unconfirmedUsers.size() == 0)
		{
			JLabel none = new JLabel(NO_REGISTRATION_PROPOSAL);
			none.setSize(new Dimension(75, 100));
			this.mainPanel.add(none);
			this.add(scrollPane);
		}
		else
		{
			for(int i = 0;i<unconfirmedUsers.size();i++)
			{
				gbc.gridy = i;
				mainPanel.add(unconfirmedUsers.get(i),gbc);
			}
			this.add(scrollPane);
		}
	}
	public void updateUnconfirmedUser()
	{
		this.unconfirmedUsers = AcceptRegistrationProposalController.getInstance().getUnconfirmedUserPanel(this);
		this.paintPanel();
		this.revalidate();
		this.repaint();
	}

}
