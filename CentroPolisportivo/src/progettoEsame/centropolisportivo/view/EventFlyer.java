package progettoEsame.centropolisportivo.view;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import progettoEsame.centropolisportivo.view.actionListener.EventCardController;

public class EventFlyer extends JPanel{

	private static final long serialVersionUID = 1L;
	private ArrayList<EventCard> allEvent; 
	private GridBagConstraints gbc;
	private Template template;
	
	public EventFlyer(Template template) 
	{
		this.template=template;
		gbc = new GridBagConstraints();
		
		JPanel mainPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(mainPanel);
		scrollPane.setLayout(new ScrollPaneLayout());
		scrollPane.setMinimumSize(new Dimension(500, 700));
		scrollPane.setPreferredSize(new Dimension(500, 700));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		try {
			EventCardController.getInstance().setTemplate(this.template);
			this.allEvent = EventCardController.getInstance().getAllEventCard();
			if(allEvent.size() == 0)
			{
				JLabel none = new JLabel(ConstantClass.NO_EVENT);
				none.setSize(new Dimension(75, 100));
				this.add(none);
			}
			else
			{
				for(int i = 0;i<allEvent.size();i++)
				{
					gbc.gridy = i;
					mainPanel.add(allEvent.get(i),gbc);
				}
				this.add(scrollPane);
			}
		} catch (SQLException e) {
			
		}
	}
}
