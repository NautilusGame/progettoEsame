package progettoEsame.centropolisportivo.view;

import static progettoEsame.centropolisportivo.view.ConstantClass.NO_REGISTRATION_PROPOSAL;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import progettoEsame.centropolisportivo.view.actionListener.AcceptPaymentController;

public class AcceptPaymentProposal extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<JPanel> unconfirmedUsers;
	private GridBagConstraints gbc;
	private JLabel msg;
	private JPanel mainPanel;
	private JScrollPane scrollPane;

	public AcceptPaymentProposal(){
		this.setLayout(new BorderLayout());
		this.msg = new JLabel();
		gbc = new GridBagConstraints();
		this.mainPanel = new JPanel();
		this.scrollPane = new JScrollPane(this.mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setLayout(new ScrollPaneLayout());
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setMinimumSize(new Dimension(500, 700));
		scrollPane.setPreferredSize(new Dimension(500, 700));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.scrollPane.setBorder(null);
		this.paintPanel();
	}
	
	public void paintPanel()
	{
		this.mainPanel.removeAll();
		this.unconfirmedUsers = AcceptPaymentController.getInstance().getUnconfirmedPayment(this);
		if(unconfirmedUsers == null)
		{
			JLabel none = new JLabel(NO_REGISTRATION_PROPOSAL);
			none.setSize(new Dimension(75, 100));
			this.mainPanel.add(none,BorderLayout.NORTH);
			this.add(scrollPane,BorderLayout.CENTER);
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
		this.unconfirmedUsers = AcceptPaymentController.getInstance().getUnconfirmedPayment(this);
		this.paintPanel();
		this.revalidate();
		this.repaint();
	}
	public void addMessageToPanel(JLabel msg)
	{
		this.msg = msg;
		gbc.gridx = 0;
		gbc.gridy = 5;
		this.add(msg,BorderLayout.SOUTH);
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

}
