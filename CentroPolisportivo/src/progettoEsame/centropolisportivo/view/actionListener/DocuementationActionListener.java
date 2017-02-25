package progettoEsame.centropolisportivo.view.actionListener;

import static progettoEsame.centropolisportivo.view.ConstantClass.ACCEPT_PAYMENT_PROPOSAL_ACCEPT_BUTTON_NAME;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;

public class DocuementationActionListener implements ActionListener
{
	public DocuementationActionListener()
	{
		
	}

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals("doc"))
		{
			if (Desktop.isDesktopSupported()) {
	            try 
	            {
	                File myFile = new File( "C:\\Users\\Utente\\Desktop\\test.pdf");
	                Desktop.getDesktop().open(myFile);
	            }
	            catch (IOException ex)
	            {
	            
	            }
	        }
		}
		
	}
}
