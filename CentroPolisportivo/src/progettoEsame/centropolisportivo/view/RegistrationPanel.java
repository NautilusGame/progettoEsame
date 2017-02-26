package progettoEsame.centropolisportivo.view;

import java.awt.BorderLayout;
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
import static progettoEsame.centropolisportivo.view.ConstantClass.*;

public class RegistrationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<JPanel> subscriptionPanel;
	private JPanel mainPanel;
	private JScrollPane scrollPane;
	private int numberOfPanel;
	private JLabel msg;
	private ImageIcon pdfIcon;
	private JLabel pdfLabel;
	private GridBagConstraints gbc;
	private JPanel otherPanel;
	private JPanel contentPanel;
	public RegistrationPanel() {
		this.init();
		this.add(contentPanel);
	}

	private void init()
	{
		this.gbc = new GridBagConstraints();
		this.otherPanel = new JPanel();
		this.contentPanel = new JPanel(new GridBagLayout());
		this.otherPanel.setVisible(false);
		this.mainPanel = new JPanel(new GridLayout(this.numberOfPanel, 1));
		this.scrollPane = new JScrollPane(this.mainPanel);
		this.pdfLabel = new JLabel();
		this.pdfIcon = new ImageIcon(new ImageIcon("../progettoEsame/image/SystemImage/Remove_Document-128.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		this.pdfLabel.setIcon(pdfIcon);
		this.pdfLabel.addMouseListener(new RegistrationPanelMouseListener());
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.contentPanel.add(pdfLabel,gbc);
		this.msg = new JLabel();
		this.subscriptionPanel = RegistrationPanelController.getInstance().init(this);
		if(subscriptionPanel == null)
		{
			this.pdfLabel.setVisible(false);
			this.add(new JLabel(REGISTRATION_PANEL_NO_REGISTRATION));
		}
		else
		{
			this.numberOfPanel = this.subscriptionPanel.size();
			for(int i = 0;i<this.numberOfPanel;i++)
			{
				this.mainPanel.add(this.subscriptionPanel.get(i));
			}

			gbc.gridx = 0;
			gbc.gridy = 1;
			this.contentPanel.add(scrollPane,gbc);
		}

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

	public void makeInvisibleMainPanel(JPanel otherPanel)
	{
		this.otherPanel = otherPanel;
		this.contentPanel.setVisible(false);
		this.add(otherPanel);
		this.revalidate();
		this.repaint();
	}


	public void makeVisibleMainPanel()
	{
		this.scrollPane.setVisible(true);
		this.otherPanel.setVisible(false);
	}
}

