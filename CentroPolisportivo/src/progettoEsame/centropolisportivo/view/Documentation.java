package progettoEsame.centropolisportivo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import progettoEsame.centropolisportivo.view.actionListener.DocuementationActionListener;

public class Documentation extends JPanel 
{
	private JButton openDoc;
	private JLabel info;
	private BufferedImage logoImg;
	private JLabel logo;
	
	public Documentation()
	{
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		
		openDoc=new JButton("Open Documentation");
		info= new JLabel();
		logo=new JLabel();
		
		info.setText("<html> <center> Elaborato software anno 2016/2017 sviluppato"
				+ "<br>dagli studenti Benito Taccardi e Luca Podo."
				+ "<br>Per accedere alla documentazione del progetto"
				+ "<br> basta cliccare sul pulsante 'Open Documentation'."
				+ "<br>Si potrebbero avere problemi nel caso in cui"
				+ "<br>non è presente un programma"
				+ "<br>per apprire i file pdf.<br> "
				+ "<br>Si può risolvere settando come programma di dafault "
				+ "<br>per i pdf il browser.</center></html>");
		info.setFont(new Font("Arial", Font.BOLD, 20));
		
		openDoc.addActionListener(new DocuementationActionListener());
		openDoc.setActionCommand("doc");
		
		try 
		{
			logoImg = ImageIO.read(new File("image/SystemImage/logo.jpg"));
			logo= new JLabel(new ImageIcon(logoImg));			
		} 
		catch (IOException ex)
		{
			logo= new JLabel("Error 404:Image not Found");
		}
		
		this.add(logo,	BorderLayout.NORTH);
		this.add(info,	BorderLayout.CENTER);
		this.add(openDoc,BorderLayout.SOUTH);
	}
}
