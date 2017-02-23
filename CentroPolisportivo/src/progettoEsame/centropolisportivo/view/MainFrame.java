package progettoEsame.centropolisportivo.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.dbConnection.DbConnection;

public class MainFrame extends JFrame {
	
	private LoginRegister loginRegisterPanel;
	private AcceptRegistrationProposal centralManagerRegistrationProposal;
	
	public MainFrame(String title,Dimension d)
	{
		Template template = new Template(this);
		DbConnection.connetti("sports_center", "root", "");
		this.setTitle(title);
		this.setSize(d);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(template);
	}

}