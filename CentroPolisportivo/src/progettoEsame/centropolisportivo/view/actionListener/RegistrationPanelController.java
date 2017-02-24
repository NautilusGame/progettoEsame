package progettoEsame.centropolisportivo.view.actionListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import progettoEsame.centropolisportivo.business.ActivityBusiness;
import progettoEsame.centropolisportivo.business.EventBusiness;
import progettoEsame.centropolisportivo.business.PaymentBusiness;
import progettoEsame.centropolisportivo.business.RegistrationBusiness;
import progettoEsame.centropolisportivo.business.RegistrationCalendarBusiness;
import progettoEsame.centropolisportivo.business.ScheduleBusiness;
import progettoEsame.centropolisportivo.business.Session;
import progettoEsame.centropolisportivo.exception.SessionException;
import progettoEsame.centropolisportivo.model.Activity;
import progettoEsame.centropolisportivo.model.Event;
import progettoEsame.centropolisportivo.model.Registration;
import progettoEsame.centropolisportivo.model.RegistrationCalendar;
import progettoEsame.centropolisportivo.model.Schedule;
import progettoEsame.centropolisportivo.view.RegistrationPanel;

import static progettoEsame.centropolisportivo.view.ConstantClass.*;

public class RegistrationPanelController {

	private ArrayList<JPanel> subscriptionPanel; 
	private ArrayList<Registration> registrations;
	private RegistrationPanel registrationPanel;
	private static RegistrationPanelController instance;


	public static synchronized RegistrationPanelController getInstance()
	{
		if(instance == null)
			return new RegistrationPanelController();
		return instance;
	}

	public ArrayList<JPanel> init(RegistrationPanel registrationPanel)
	{
		this.registrationPanel = registrationPanel;

		try {
			String memberEmail = Session.getInstance().getEmail();
			this.subscriptionPanel = new ArrayList<>();
			this.registrations = RegistrationBusiness.getInstance().getAllRegisteredRegistration(memberEmail);
			for(int i = 0;i<registrations.size();i++)
			{
				JPanel tmpPanel = new JPanel(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();
				JButton modifyButton = new JButton(REGISTRATION_PANEL_MODIFY_BUTTON);
				

				gbc.gridx = 0;
				gbc.gridy = 0;
				if(registrations.get(i).getActivity()==null)
				{
					modifyButton.setName((registrations.get(i).getEvent().getId()).toString());
					modifyButton.setActionCommand(REGISTRATION_PANEL_MODIFY_BUTTON_EVENT_ACTION_CMD);
					modifyButton.addActionListener(new RegistrationPanelActionListener(this.registrationPanel));
					int confirmed = PaymentBusiness.getInstance().findByEventAndMember(registrations.get(i).getEvent().getId(), memberEmail).getConfirmed();
					if( confirmed == 0)
					{
						tmpPanel.setBackground(Color.orange);
					}
					else if(confirmed == 1)
					{
						tmpPanel.setBackground(Color.green);
					}
					else
					{
						tmpPanel.setBackground(Color.red);
					}
					tmpPanel.add(new JLabel("Tipology: Event"),gbc);
				}
				else
				{
					modifyButton.setName(Integer.valueOf((registrations.get(i).getActivity().getId())).toString());
					modifyButton.setActionCommand(REGISTRATION_PANEL_MODIFY_BUTTON_ACTIVITY_ACTION_CMD);	
					int confirmed = PaymentBusiness.getInstance().findByActivityAndMember(registrations.get(i).getActivity().getId(), memberEmail).getConfirmed();
					if( confirmed == 0)
					{
						tmpPanel.setBackground(Color.orange);
					}
					else if(confirmed == 1)
					{
						tmpPanel.setBackground(Color.green);
					}
					else
					{
						tmpPanel.setBackground(Color.red);
					}
					tmpPanel.add(new JLabel("Tipology: Activity"),gbc);
				}

				gbc.gridx = 1;
				gbc.gridy = 0;
				if(registrations.get(i).getActivity() == null)
				{
					tmpPanel.add(new JLabel("Name: " + ActivityBusiness.getInstance().findByID(registrations.get(i).getEvent().getId()).getName()),gbc);
				}
				else
				{
					tmpPanel.add(new JLabel("Name: " + ActivityBusiness.getInstance().findByID(registrations.get(i).getActivity().getId()).getName()),gbc);
				}

				gbc.gridx = 0;
				gbc.gridy = 1;
				tmpPanel.add(new JLabel("Enrollment data: " + registrations.get(i).getDate()),gbc);

				gbc.gridx = 1;
				gbc.gridy = 1;
				tmpPanel.add(new JLabel("Deadline: " + registrations.get(i).getDeadline()),gbc);

				gbc.gridx = 2;
				gbc.gridy = 0;
				tmpPanel.add(modifyButton,gbc);
				this.subscriptionPanel.add(tmpPanel);
			}

		} catch (SQLException | SessionException e) {

			e.printStackTrace();
		}
		return this.subscriptionPanel;
	}

	public JPanel getPDFsubscription()
	{
		JPanel returnPanel = new JPanel();
		returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.Y_AXIS));
		ArrayList<JPanel>subscription = new ArrayList<>();
		try {
			String memberEmail = Session.getInstance().getEmail();
			this.registrations = RegistrationBusiness.getInstance().getAllRegisteredRegistration(memberEmail);
			for(int i = 0;i<registrations.size();i++)
			{
				JPanel tmpPanel = new JPanel(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();


				gbc.gridx = 0;
				gbc.gridy = 0;
				if(registrations.get(i).getActivity()==null)
				{
					int confirmed = PaymentBusiness.getInstance().findByActivityAndMember(registrations.get(i).getEvent().getId(), memberEmail).getConfirmed();
					if( confirmed == 0)
					{
						tmpPanel.setBackground(Color.orange);
					}
					else if(confirmed == 1)
					{
						tmpPanel.setBackground(Color.green);
					}
					else
					{
						tmpPanel.setBackground(Color.red);
					}
					tmpPanel.add(new JLabel("Tipology: Event"),gbc);

				}
				else
				{
					int confirmed = PaymentBusiness.getInstance().findByActivityAndMember(registrations.get(i).getActivity().getId(), memberEmail).getConfirmed();
					if( confirmed == 0)
					{
						tmpPanel.setBackground(Color.orange);
					}
					else if(confirmed == 1)
					{
						tmpPanel.setBackground(Color.green);
					}
					else
					{
						tmpPanel.setBackground(Color.red);
					}
					tmpPanel.add(new JLabel("Tipology: Activity"),gbc);

					gbc.gridx = 1;
					gbc.gridy = 0;
					tmpPanel.add(new JLabel("Description: " + registrations.get(i).getActivity().getDescription()),gbc);
				}

				gbc.gridx = 2;
				gbc.gridy = 0;
				if(registrations.get(i).getActivity() == null)
				{
					tmpPanel.add(new JLabel("Name: " + ActivityBusiness.getInstance().findByID(registrations.get(i).getEvent().getId()).getName()),gbc);
				}
				else
				{
					tmpPanel.add(new JLabel("Name: " + ActivityBusiness.getInstance().findByID(registrations.get(i).getActivity().getId()).getName()),gbc);
				}

				gbc.gridx = 0;
				gbc.gridy = 1;
				tmpPanel.add(new JLabel("Enrollment data: " + registrations.get(i).getDate()),gbc);

				gbc.gridx = 1;
				gbc.gridy = 1;
				tmpPanel.add(new JLabel("Deadline: " + registrations.get(i).getDeadline()),gbc);

				gbc.gridx = 0;
				gbc.gridy = 2;
				tmpPanel.add(new JLabel("Cost: " + Double.valueOf(registrations.get(i).getCost())),gbc);

				if(registrations.get(i).getActivity() != null)
				{

					gbc.gridx = 1;
					gbc.gridy = 2;
					tmpPanel.add(new JLabel("Level: " + registrations.get(i).getLevel().getName()),gbc);
				}
				
				if(registrations.get(i).getActivity() != null)
				{
					JPanel schedulePanel = new JPanel();
					schedulePanel.setLayout(new BoxLayout(schedulePanel, BoxLayout.LINE_AXIS));
					ArrayList<RegistrationCalendar> registrationCalendar = RegistrationCalendarBusiness.findCalendarByRegistrations(registrations.get(i).getId());
					for(int j = 0;j<registrationCalendar.size();j++)
					{
						JPanel tmpSchedulePanel = new JPanel();
						tmpSchedulePanel.setLayout(new GridLayout(1, 1));
						Schedule tmpSchedule = ScheduleBusiness.getInstance().findById(registrationCalendar.get(i).getSchedule().getId());
						tmpSchedulePanel.add(new JLabel("Day :" + tmpSchedule.getDay()));
						tmpSchedulePanel.add(new JLabel("Time :" + tmpSchedule.getTime()));
						schedulePanel.add(tmpSchedulePanel);
					}
					
					gbc.gridx = 0;
					gbc.gridy = 3;
					tmpPanel.add(schedulePanel,gbc);
				}

				returnPanel.add(tmpPanel);
			}
		} catch (SQLException | SessionException e) {

			e.printStackTrace();
		}

		return returnPanel;
	}

}
