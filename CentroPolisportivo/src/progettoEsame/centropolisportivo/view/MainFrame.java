package progettoEsame.centropolisportivo.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import progettoEsame.centropolisportivo.business.Session;

public class MainFrame extends JFrame {
	
	private LoginRegister loginRegisterPanel;
	private CenterManagerRegistrationProposal centralManagerRegistrationProposal;
	
	public MainFrame(String title,Dimension d)
	{
		loginRegisterPanel = new LoginRegister(this);
		this.setTitle(title);
		this.setSize(d);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(loginRegisterPanel);
	}

}