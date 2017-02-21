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
		loginRegisterPanel = new LoginRegister(this);
		DbConnection.connetti("sports_center", "root", "");
		Session.getInstance().createSession("ciao", "center_manager");
		this.setTitle(title);
		this.setSize(d);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(loginRegisterPanel);
	}

}