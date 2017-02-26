package progettoEsame.centropolisportivo.view;


import static progettoEsame.centropolisportivo.view.ConstantClass.*;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import progettoEsame.centropolisportivo.view.actionListener.ReviewCardController;

public class ReviewsQueue extends JScrollPane
{
	private static final long serialVersionUID = 1L;
	private ArrayList<ReviewCard> allReviews;
	private GridBagConstraints gbc;
	
	public ReviewsQueue(RegistrationCard rc) 
	{
		gbc = new GridBagConstraints();
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		try 
		{
			this.allReviews =ReviewCardController.getInstance().getAllReviewCard(rc.getIdActivity());
			if(this.allReviews==null)
			{
				JLabel none = new JLabel(NO_REVIEWS);
				none.setSize(new Dimension(75, 100));
				this.setViewportView(none);
			}
			else
			{
				for(int i = 0;i<allReviews.size();i++)
				{
					gbc.gridy = i;
					mainPanel.add(allReviews.get(i),gbc);
				}
				this.setViewportView(mainPanel);
			}
		} catch (SQLException e) {
			// TODO invia messaggio di errore
		}
	}
}