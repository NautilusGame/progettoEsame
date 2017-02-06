package progettoEsame.centropolisportivo.view;

import java.awt.Color;

import javax.swing.JLabel;


public class Message{
	
	private static Message instance;
	private JLabel labelMessage;
	
	public static synchronized Message getInstance()
	{
		if(instance==null)
			instance = new Message();
		return instance;
	}
	
	public JLabel printSuccessMsg(String msg)
	{
		
		this.labelMessage = new JLabel(msg);
		this.labelMessage.setForeground(Color.GREEN);
		return this.labelMessage;
	}
	

	public JLabel printWarningMsg(String msg)
	{
		
		this.labelMessage = new JLabel(msg);
		this.labelMessage.setForeground(Color.YELLOW);
		return this.labelMessage;
	}
	

	public JLabel printErrorMsg(String msg)
	{
		
		this.labelMessage = new JLabel(msg);
		this.labelMessage.setForeground(Color.RED);
		return this.labelMessage;
	}

}
