package progettoEsame.centropolisportivo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import progettoEsame.centropolisportivo.view.actionListener.RegistrationPanelController;
import progettoEsame.centropolisportivo.view.actionListener.RegistrationPanelMouseListener;

public class RegistrationPanel extends JPanel {

	private ArrayList<JPanel> subscriptionPanel;
	private JPanel mainPanel;
	private JScrollPane scrollPane;
	private int numberOfPanel;
	private JLabel msg;
	private ImageIcon pdfIcon;
	private JLabel pdfLabel;
	private GridBagConstraints gbc;
	public RegistrationPanel() {
		this.setLayout(new GridBagLayout());
		this.init();
	}

	private void init()
	{
		this.gbc = new GridBagConstraints();
		this.mainPanel = new JPanel(new GridLayout(this.numberOfPanel, 1));
		this.scrollPane = new JScrollPane(this.mainPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.pdfLabel = new JLabel();
		this.pdfIcon = new ImageIcon(new ImageIcon("../progettoEsame/image/SystemImage/pulsante-stampa_318-77516.jpg").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		this.pdfLabel.setIcon(pdfIcon);
		this.pdfLabel.addMouseListener(new RegistrationPanelMouseListener());
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(pdfLabel,gbc);
		this.msg = new JLabel();
		this.subscriptionPanel = RegistrationPanelController.getInstance().init(this);
		this.numberOfPanel = this.subscriptionPanel.size();
		for(int i = 0;i<this.numberOfPanel;i++)
		{
			this.mainPanel.add(this.subscriptionPanel.get(i));
		}
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(scrollPane,gbc);
	}
	
	public void addMessageToPanel(JLabel msg)
	{
		this.msg = msg;
		this.add(this.msg,BorderLayout.SOUTH);
		this.revalidate();
		this.repaint();
	}
	
	public void removeMessageToPanel()
	{

		this.remove(this.msg);
		this.revalidate();
		this.repaint();
	}
}

