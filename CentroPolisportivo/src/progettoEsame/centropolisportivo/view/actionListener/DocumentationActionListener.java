package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DocumentationActionListener implements ActionListener
{
	public DocumentationActionListener()
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
